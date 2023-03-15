package su.dedvano.goods.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public record ProductCategoryRequest(
        @NotBlank
        String name,
        @PositiveOrZero(message = "Order must be positive or 0")
        int orderInReport
) {

}
