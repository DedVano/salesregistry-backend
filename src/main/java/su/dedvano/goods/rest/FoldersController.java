package su.dedvano.goods.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import su.dedvano.goods.converter.FolderMapper;
import su.dedvano.goods.converter.IncludedProductMapper;
import su.dedvano.goods.domain.IncludedProduct;
import su.dedvano.goods.dto.response.IncludedProductResponse;
import su.dedvano.goods.dto.request.FolderRequest;
import su.dedvano.goods.dto.request.IncludeItemRequest;
import su.dedvano.goods.dto.response.FolderResponse;
import su.dedvano.goods.service.FolderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/folders")
public class FoldersController {

    private final FolderService folderService;
    private final FolderMapper folderMapper;
    private final IncludedProductMapper includedProductMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderResponse create(@RequestBody @Valid FolderRequest request) {
        return folderMapper.toResponse(folderService.create(request));
    }

    @PutMapping("/{id}")
    public FolderResponse update(@PathVariable UUID id, @RequestBody @Valid FolderRequest request) {
        return folderMapper.toResponse(folderService.update(id, request));
    }

    @GetMapping("/{id}/products")
    public List<IncludedProductResponse> showProducts(@PathVariable UUID id) {
        Set<IncludedProduct> includedProducts = folderService.showIncludedProducts(id);
        List<IncludedProduct> products = includedProducts.stream().toList();
        return includedProductMapper.toResponse(products);
    }

    @PostMapping("/{id}")
    public void includeProduct(@PathVariable UUID id, @RequestBody IncludeItemRequest includeItemRequest) {
        folderService.includeProduct(id, includeItemRequest);
    }

}
