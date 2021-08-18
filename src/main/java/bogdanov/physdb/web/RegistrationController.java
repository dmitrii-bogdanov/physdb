package bogdanov.physdb.web;

import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import bogdanov.physdb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
//        model.addAttribute("userForm", new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping("/registration")
    @ResponseBody
    public UserDTO registerUser(@ModelAttribute("userForm") UserRegistrationDTO user) {
        return userService.saveUser(user);
    }

}
