package su.dedvano.converter;

import org.mapstruct.Mapper;
import su.dedvano.domain.GoodEntity;
import su.dedvano.domain.GoodsCategory;
import su.dedvano.dto.GoodEntityResponse;
import su.dedvano.dto.GoodsCategoryResponse;

import java.util.Set;

@Mapper
public interface GoodEntityMapper {

    GoodEntityResponse toResponse(GoodEntity entity);

    Set<GoodEntityResponse> toResponse(Set<GoodEntity> entities);

    GoodsCategoryResponse toResponse(GoodsCategory category);
}
