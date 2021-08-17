package bogdanov.physdb;

import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.services.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Initialization {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    void run() {
        System.out.println("RUN");
    }

}
