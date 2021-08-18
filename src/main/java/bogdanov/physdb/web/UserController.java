package bogdanov.physdb.web;

import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import bogdanov.physdb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    //region Autowired Setters
    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }
    //endregion

    @PostMapping
    public UserDTO addUser(@RequestBody UserRegistrationDTO user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    //TODO DELETE
    @PostMapping("/login")
    public UserDTO loginIsSuccessful(@RequestBody UserRegistrationDTO user) {
        return userService.loginIsSuccessful(user);
    }

    //TODO DELETE
    @GetMapping("/allentities")
    public List<UserEntity> getAllEntities(){
        return userService.getAllEntities();
    }

}
