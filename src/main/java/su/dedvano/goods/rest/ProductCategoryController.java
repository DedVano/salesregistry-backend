package su.dedvano.goods.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.dedvano.goods.converter.ProductCategoryMapper;
import su.dedvano.goods.converter.ProductMapper;
import su.dedvano.goods.dto.response.ProductCategoryResponse;
import su.dedvano.goods.dto.response.ProductResponse;
import su.dedvano.goods.service.ProductCategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@Tag(name = "Категории товаров")
public class ProductCategoryController {

    private final ProductCategoryService categoryService;
    private final ProductCategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Operation(summary = "показать все")
    @GetMapping
    public List<ProductCategoryResponse> findAll() {
        return categoryMapper.toResponse(categoryService.findAll());
    }

    @Operation(summary = "найти по id")
    @GetMapping("/{id}")
    public ProductCategoryResponse findById(@PathVariable UUID id) {
        return categoryMapper.toResponse(categoryService.findById(id));
    }

    @Operation(summary = "показать товары в категории")
    @GetMapping("/{id}/products")
    public List<ProductResponse> showProducts(@PathVariable UUID id) {
        return productMapper.toResponse(categoryService.showProducts(id));
    }

}
