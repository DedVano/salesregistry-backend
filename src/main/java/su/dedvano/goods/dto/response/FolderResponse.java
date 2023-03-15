package su.dedvano.goods.dto.response;

import java.util.UUID;

public record FolderResponse(
        UUID id,
        String name,
        int sizeRows,
        int sizeColumns,
        int color
) {

}
