package su.dedvano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.dedvano.domain.GoodEntity;

import java.util.UUID;

public interface GoodRepository extends JpaRepository<GoodEntity, UUID> {
}
