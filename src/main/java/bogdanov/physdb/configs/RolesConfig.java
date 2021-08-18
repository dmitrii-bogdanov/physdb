package bogdanov.physdb.configs;

import bogdanov.physdb.database.entities.RoleEntity;
import bogdanov.physdb.database.entities.UserEntity;
import bogdanov.physdb.database.repositories.RoleRepository;
import bogdanov.physdb.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@PropertySource("classpath:admin.properties")
public class RolesConfig {


    @Autowired
    private void initRoles(RoleRepository roleRepository,
                           @Qualifier("admin") RoleEntity adminRole,
                           @Qualifier("user") RoleEntity userRole) {
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
    }

    @Autowired
    private void initAdmin(UserRepository userRepository,
                           RoleRepository roleRepository,
                           @Value("${admin.username}") String username,
                           @Value("${admin.password}") String password) {
        UserEntity admin = new UserEntity();
        admin.setUsername(username);
        admin.setPassword(new BCryptPasswordEncoder(13).encode(password));
        admin.setEmail("");
        admin.setFirstname("");
        admin.setLastname("");
        admin.setEnabled(true);
        admin.getRoles().add(roleRepository.getByName("ROLE_USER"));
        admin.getRoles().add(roleRepository.getByName("ROLE_ADMIN"));
        userRepository.save(admin);
    }


    @Bean
    @Qualifier("admin")
    public RoleEntity getAdminRole() {
        return new RoleEntity(1L, "ROLE_ADMIN");
    }

    @Bean
    @Qualifier("user")
    public RoleEntity getUserRole(){
        return new RoleEntity(2L, "ROLE_USER");
    }

}
