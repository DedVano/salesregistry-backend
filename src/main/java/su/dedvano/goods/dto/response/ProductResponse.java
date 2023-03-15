package su.dedvano.goods.dto.response;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        int price,
        boolean variablePrice,
        ProductCategoryResponse category,
        int color
) {

}
