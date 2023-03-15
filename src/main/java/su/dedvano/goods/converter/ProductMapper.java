package su.dedvano.goods.converter;

import org.mapstruct.Mapper;
import su.dedvano.goods.domain.Product;
import su.dedvano.goods.domain.ProductCategory;
import su.dedvano.goods.dto.response.ProductCategoryResponse;
import su.dedvano.goods.dto.response.ProductResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponse(List<Product> products);

    ProductCategoryResponse toResponse(ProductCategory category);

}
