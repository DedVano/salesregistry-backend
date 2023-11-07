package su.dedvano.goods.dto.request;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Validated
public record ProductRequest(
        @NotBlank
        String name,
        @PositiveOrZero(message = "price must be positive or 0")
        int price,
        boolean variablePrice,
        @NotNull
        UUID categoryId,
        @Min(value = 0, message = "color must be between 0 and 16777215")
        @Max(value = 16777215, message = "color must be between 0 and 16777215")
        int color
) {

}
