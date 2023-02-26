package su.dedvano.dto;

import java.util.UUID;

public record ProductsCategoryResponse(
        UUID id,
        String name,
        int orderInReport
) {
}
