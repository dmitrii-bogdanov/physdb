package bogdanov.physdb.database.repositories;

import bogdanov.physdb.database.entities.InfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<InfoEntity, Long> {
}
