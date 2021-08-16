package bogdanov.physdb.database.repositories;

import bogdanov.physdb.database.entities.FileTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTypeRepository extends JpaRepository<FileTypeEntity, Long> {
}
