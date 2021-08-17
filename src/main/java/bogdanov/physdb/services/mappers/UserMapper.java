package bogdanov.physdb.services.mappers;

import bogdanov.physdb.database.entities.ProjectEntity;
import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private BCryptPasswordEncoder encoder;

    @Autowired
    private void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    UserEntity convert(UserRegistrationDTO user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setEmail(user.getEmail());

        return userEntity;
    }

    UserEntity convert(UserDTO user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(user.getUsername());
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setEmail(user.getEmail());

        return userEntity;
    }

    UserDTO convert(UserEntity user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setIsEnabled(user.isEnabled());

//        if (!user.getCreatedProjects().isEmpty()) {
//            List<Long> createdProjects = new ArrayList<>();
//            for (ProjectEntity p : user.getCreatedProjects()){
//                createdProjects.add(p.getId());
//            }
//            userDTO.setCreatedProjects(createdProjects);
//        }

        return userDTO;
    }

}
