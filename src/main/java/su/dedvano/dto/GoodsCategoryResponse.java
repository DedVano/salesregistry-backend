package su.dedvano.dto;

import java.util.UUID;

public record GoodsCategoryResponse(
        UUID id,
        String name,
        int orderInReport
) {
}
