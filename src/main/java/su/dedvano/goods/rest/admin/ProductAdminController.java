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
import su.dedvano.goods.converter.ProductMapper;
import su.dedvano.goods.dto.request.ProductRequest;
import su.dedvano.goods.dto.response.ProductResponse;
import su.dedvano.goods.service.ProductService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/products")
@Tag(name = "Управление товарами")
public class ProductAdminController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "создать новый")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid ProductRequest request) {
        return productMapper.toResponse(productService.create(request));
    }

    @Operation(summary = "изменить товар")
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable UUID id, @RequestBody @Valid ProductRequest request) {
        return productMapper.toResponse(productService.update(id, request));
    }

}
