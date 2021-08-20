package bogdanov.physdb.services.impl;

import bogdanov.physdb.database.entities.RoleEntity;
import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.database.repositories.UserRepository;
import bogdanov.physdb.dto.RoleDTO;
import bogdanov.physdb.dto.UserDTO;
import bogdanov.physdb.dto.UserRegistrationDTO;
import bogdanov.physdb.services.RoleService;
import bogdanov.physdb.services.UserService;
import bogdanov.physdb.services.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private UserRepository userRepository;
    private RoleService roleService;

    //region Autowired Setters
    @Autowired
    private void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    //endregion

    //TODO delete setEnabled()
    @Override
    public UserDTO saveUser(UserRegistrationDTO user) {
        UserEntity userEntity = mapper.convert(user);
        userEntity.setRoles(
                Collections.singleton(mapper.convert(roleService.getByName("ROLE_USER")))
        );
        userEntity.setEnabled(true);
        return mapper.convert(
                userRepository.save(userEntity)
        );
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(mapper::convert).collect(Collectors.toList());
    }

    //TODO DELETE TEST METHODS
    //region TEST METHODS
    @Override
    public UserDTO addTestUser() {
        UserEntity user = new UserEntity();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setName("ROLE_USER");
        System.out.println(roleDTO);
        System.out.println(roleService.addRole(roleDTO));
        user.setUsername("username");
        user.setPassword(new BCryptPasswordEncoder(11).encode("password"));
        user.setEmail("email");
        user.setFirstname("firstname");
        user.setLastname("lastname");
        user.getRoles().add(mapper.convert(roleDTO));
        System.out.println(user);
        UserEntity saved = userRepository.save(user);
        System.out.println(saved);

        return mapper.convert(saved);
    }

    @Override
    public List<UserEntity> getAllEntities() {
        return userRepository.findAll();
    }

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDTO loginIsSuccessful(UserRegistrationDTO user) {
        System.out.println(user);
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        System.out.println(userEntity);
        if (userEntity != null) {
            System.out.println(encoder.matches(user.getPassword(), userEntity.getPassword()));
            if (encoder.matches(user.getPassword(), userEntity.getPassword())){
                return mapper.convert(userEntity);
            }
        }
        return null;
    }
    //endregion

    @Override
    public UserDTO getById(long id) {
        return mapper.convert(userRepository.getById(id));
    }

    @Override
    public UserDTO getByUsername(String username) {
            return mapper.convert(userRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
