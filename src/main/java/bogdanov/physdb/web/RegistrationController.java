package bogdanov.physdb.web;

import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.dto.RoleDTO;
import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import bogdanov.physdb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

import java.security.Principal;
import java.util.List;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserRegistrationDTO userRegistrationDTO,
                               BindingResult errors, Model model) {
        model.addAttribute("user", userService.saveUser(userRegistrationDTO));
        return "successfulRegistration";
    }

    @GetMapping("/users/current")
    @ResponseBody
    public UserDTO getCurrentUser(Principal user) {
        System.out.println(user.getName());
        System.out.println("manual " + userService.getByUsername("admin"));
        System.out.println("user " + userService.getByUsername(user.getName()));
        return userService.getByUsername(user.getName());
    }

    @GetMapping("/users/c")
    @ResponseBody
    public UserDTO getCurrentUserWithAnnotation(@AuthenticationPrincipal UserEntity user) {
        System.out.println(user.toString());
        return userService.getByUsername(user.getUsername());
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "usersTable";
    }

}
