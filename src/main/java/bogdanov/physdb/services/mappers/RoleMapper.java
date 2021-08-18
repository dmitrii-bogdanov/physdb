package bogdanov.physdb.services.mappers;

import bogdanov.physdb.database.entities.RoleEntity;
import bogdanov.physdb.dto.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    RoleEntity convert(RoleDTO role) {
        return new RoleEntity(role.getId(), role.getName());
    }

    RoleDTO convert(RoleEntity role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

}
