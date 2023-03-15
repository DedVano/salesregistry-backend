package su.dedvano.goods.dto.response;

import java.util.UUID;

public record ProductCategoryResponse(
        UUID id,
        String name,
        int orderInReport
) {

}
