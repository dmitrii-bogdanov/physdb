package bogdanov.physdb.services.impl;

import bogdanov.physdb.database.entities.RoleEntity;
import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.database.repositories.UserRepository;
import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import bogdanov.physdb.services.UserService;
import bogdanov.physdb.services.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private UserRepository userRepository;

    //region Autowired Setters
    @Autowired
    private void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //endregion

    @Override
    public UserDTO saveUser(UserRegistrationDTO user) {
        UserEntity userEntity = mapper.convert(user);
        userEntity.setRoles(
                Collections.singleton(new RoleEntity(1L, "ROLE_USER"))
        );
        return mapper.convert(
                userRepository.save(userEntity)
        );
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(mapper::convert).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
