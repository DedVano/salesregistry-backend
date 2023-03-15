package su.dedvano.goods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.dedvano.goods.domain.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {

    List<ProductCategory> findAllByDeletedFalse();

}
