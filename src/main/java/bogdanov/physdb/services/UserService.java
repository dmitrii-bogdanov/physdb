package bogdanov.physdb.services;

import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    public UserDTO saveUser(UserRegistrationDTO user);

    public List<UserDTO> getAll();

    public UserDTO addTestUser();

    public List<UserEntity> getAllEntities();

    public UserDTO loginIsSuccessful(UserRegistrationDTO user);

    public UserDTO getById(long id);

    public UserDTO getByUsername(String username);

}
