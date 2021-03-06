package bogdanov.physdb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {

    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean isEnabled;
    private List<Long> createdProjects;

}
