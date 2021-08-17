package bogdanov.physdb;

import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import bogdanov.physdb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Initialization {

    private UserService userService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void run() {

        UserRegistrationDTO user = new UserRegistrationDTO();
        user.setUsername("dimbog");
        user.setPassword("AFgJ^!5vd@6QTpLt");
        user.setFirstname("Dmitrii");
        user.setLastname("Bogdanov");
        user.setEmail("dimbog1@gmail.com");

        UserDTO saved = userService.saveUser(user);

        List<UserDTO> list = userService.getAll();

        System.out.println(user);
        System.out.println(saved);
//        System.out.println(encoder.matches(user.getPassword(), saved.getPassword()));
        list.forEach(System.out::println);
    }

}
