package bogdanov.physdb.database.repositories;

import bogdanov.physdb.database.entities.ConferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.charset.CodingErrorAction;

@Repository
public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {
}
