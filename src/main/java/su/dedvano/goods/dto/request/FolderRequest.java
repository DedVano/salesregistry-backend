package su.dedvano.goods.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public record FolderRequest(
        @NotBlank
        String name,
        @Positive(message = "sizeRows must be greater then 0")
        int sizeRows,
        @Positive(message = "sizeColumns must be greater then 0")
        int sizeColumns,
        @Min(value = 0, message = "color must be between 0 and 16777215")
        @Max(value = 16777215, message = "color must be between 0 and 16777215")
        int color
) {

}
