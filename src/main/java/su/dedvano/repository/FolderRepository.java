package su.dedvano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.dedvano.domain.Folder;

import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {
}
