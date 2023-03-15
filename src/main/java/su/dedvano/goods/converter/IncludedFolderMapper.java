package su.dedvano.goods.converter;

import org.mapstruct.Mapper;
import su.dedvano.goods.domain.Folder;
import su.dedvano.goods.domain.IncludedFolder;
import su.dedvano.goods.dto.response.FolderResponse;
import su.dedvano.goods.dto.response.IncludedFolderResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncludedFolderMapper {

    IncludedFolderResponse toResponse(IncludedFolder folder);

    List<IncludedFolderResponse> toResponse(List<IncludedFolder> products);

    FolderResponse toResponse(Folder folder);

}
