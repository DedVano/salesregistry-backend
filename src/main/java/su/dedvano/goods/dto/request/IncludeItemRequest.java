package su.dedvano.goods.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

public record IncludeItemRequest(
        @NotNull
        UUID id,
        @PositiveOrZero(message = "row must be positive or zero")
        int row,
        @PositiveOrZero(message = "column must be positive or zero")
        int column
) {

}
