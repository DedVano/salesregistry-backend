package su.dedvano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.dedvano.domain.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
