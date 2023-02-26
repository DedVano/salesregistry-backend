package su.dedvano.dto;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        int price,
        boolean variablePrice,
        ProductsCategoryResponse category,
        int color
) {
}
