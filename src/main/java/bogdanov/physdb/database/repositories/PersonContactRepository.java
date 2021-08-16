package bogdanov.physdb.database.repositories;

import bogdanov.physdb.database.entities.PersonContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactRepository extends JpaRepository<PersonContactEntity, Long> {
}
