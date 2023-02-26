package su.dedvano.dto;

import su.dedvano.domain.GoodsCategory;

import java.util.UUID;

public record GoodEntityResponse(
        UUID id,
        String name,
        int price,
        boolean variablePrice,
        GoodsCategory category,
        int color
) {
}
