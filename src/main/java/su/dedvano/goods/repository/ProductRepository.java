package su.dedvano.goods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.dedvano.goods.domain.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
