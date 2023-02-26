package su.dedvano.converter;

import org.mapstruct.Mapper;
import su.dedvano.domain.Product;
import su.dedvano.domain.ProductsCategory;
import su.dedvano.dto.ProductResponse;
import su.dedvano.dto.ProductsCategoryResponse;

import java.util.Set;

@Mapper
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    Set<ProductResponse> toResponse(Set<Product> products);

    ProductsCategoryResponse toResponse(ProductsCategory category);
}
