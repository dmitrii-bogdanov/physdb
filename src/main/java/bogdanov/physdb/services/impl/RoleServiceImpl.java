package bogdanov.physdb.services.impl;

import bogdanov.physdb.database.entities.RoleEntity;
import bogdanov.physdb.database.repositories.RoleRepository;
import bogdanov.physdb.dto.RoleDTO;
import bogdanov.physdb.services.RoleService;
import bogdanov.physdb.services.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private Mapper mapper;
    private RoleRepository roleRepository;

    //region Autowired setters
    @Autowired
    private void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    //endregion

    @Override
    public RoleDTO addRole(RoleDTO role) {
        return mapper.convert(roleRepository.save(mapper.convert(role)));
    }

    @Override
    public List<RoleDTO> getAll() {
        return roleRepository.findAll().stream().map(mapper::convert).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getById(long id) {
        return mapper.convert(roleRepository.getById(id));
    }

    @Override
    public RoleDTO getByName(String name) {
        return mapper.convert(roleRepository.getByName(name));
    }

}
