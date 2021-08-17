package bogdanov.physdb.web;

import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import bogdanov.physdb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    UserDTO addUser(@RequestBody UserRegistrationDTO user) {
        return userService.saveUser(user);
    }

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

}
