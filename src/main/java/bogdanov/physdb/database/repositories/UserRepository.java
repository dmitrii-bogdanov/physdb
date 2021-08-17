package bogdanov.physdb.database.repositories;

import bogdanov.physdb.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);

}
