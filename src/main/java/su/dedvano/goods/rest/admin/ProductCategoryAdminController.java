package su.dedvano.goods.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import su.dedvano.goods.converter.ProductCategoryMapper;
import su.dedvano.goods.dto.request.ProductCategoryRequest;
import su.dedvano.goods.dto.response.ProductCategoryResponse;
import su.dedvano.goods.service.ProductCategoryService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/categories")
@Tag(name = "Управление категориями товаров")
public class ProductCategoryAdminController {

    private final ProductCategoryService categoryService;
    private final ProductCategoryMapper categoryMapper;

    @Operation(summary = "создать новую")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategoryResponse create(@RequestBody @Valid ProductCategoryRequest request) {
        return categoryMapper.toResponse(categoryService.create(request));
    }

    @Operation(summary = "изменить категорию")
    @PutMapping("/{id}")
    public ProductCategoryResponse update(@PathVariable UUID id, @RequestBody @Valid ProductCategoryRequest request) {
        return categoryMapper.toResponse(categoryService.update(id, request));
    }

}
