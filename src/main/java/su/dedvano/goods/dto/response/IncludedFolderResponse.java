package su.dedvano.goods.dto.response;

public record IncludedFolderResponse(
        FolderResponse folder,
        int row,
        int column
) {

}
