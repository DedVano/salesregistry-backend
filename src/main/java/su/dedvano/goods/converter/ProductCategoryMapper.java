package su.dedvano.goods.converter;

import org.mapstruct.Mapper;
import su.dedvano.goods.domain.ProductCategory;
import su.dedvano.goods.dto.response.ProductCategoryResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryResponse toResponse(ProductCategory category);

    List<ProductCategoryResponse> toResponse(List<ProductCategory> categories);

}
