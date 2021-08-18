package bogdanov.physdb.configs;

import bogdanov.physdb.database.entities.RoleEntity;
import bogdanov.physdb.database.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

@Configuration
public class RolesConfig {


    @Autowired
    private void initRoles(RoleRepository roleRepository,
                           @Qualifier("admin") RoleEntity adminRole,
                           @Qualifier("user") RoleEntity userRole) {
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
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
