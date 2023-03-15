package su.dedvano.goods.converter;

import org.mapstruct.Mapper;
import su.dedvano.goods.domain.IncludedProduct;
import su.dedvano.goods.domain.Product;
import su.dedvano.goods.dto.response.IncludedProductResponse;
import su.dedvano.goods.dto.response.ProductResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncludedProductMapper {

    IncludedProductResponse toResponse(IncludedProduct product);

    List<IncludedProductResponse> toResponse(List<IncludedProduct> products);

    ProductResponse toResponse(Product product);

}
