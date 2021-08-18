package bogdanov.physdb.database.repositories;

import bogdanov.physdb.database.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    public RoleEntity getByName(String name);

}
