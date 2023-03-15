package su.dedvano.goods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.dedvano.goods.domain.Folder;

import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {

}
