package su.dedvano.goods.converter;

import org.mapstruct.Mapper;
import su.dedvano.goods.domain.Folder;
import su.dedvano.goods.dto.response.FolderResponse;

@Mapper(componentModel = "spring")
public interface FolderMapper {

    FolderResponse toResponse(Folder folder);

}
