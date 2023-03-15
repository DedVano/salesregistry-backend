package su.dedvano.goods.dto.response;

public record IncludedProductResponse(
        ProductResponse product,
        int row,
        int column
) {

}
