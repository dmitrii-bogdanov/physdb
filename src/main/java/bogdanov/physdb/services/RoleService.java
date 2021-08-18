package bogdanov.physdb.services;

import bogdanov.physdb.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    public RoleDTO addRole(RoleDTO role);

    public List<RoleDTO> getAll();

    public RoleDTO getById(long id);

    public RoleDTO getByName(String name);

}
