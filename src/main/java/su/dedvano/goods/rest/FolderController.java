package su.dedvano.goods.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import su.dedvano.goods.converter.IncludedFolderMapper;
import su.dedvano.goods.converter.IncludedProductMapper;
import su.dedvano.goods.dto.response.IncludedFolderResponse;
import su.dedvano.goods.dto.response.IncludedProductResponse;
import su.dedvano.goods.dto.request.FolderRequest;
import su.dedvano.goods.dto.request.IncludeItemRequest;
import su.dedvano.goods.dto.response.FolderResponse;
import su.dedvano.goods.service.FolderService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/folders")
@Tag(name = "Группы")
public class FolderController {

    private final FolderService folderService;
    private final FolderMapper folderMapper;
    private final IncludedFolderMapper includedFolderMapper;
    private final IncludedProductMapper includedProductMapper;

    @Operation(summary = "создать новую")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderResponse create(@RequestBody @Valid FolderRequest request) {
        return folderMapper.toResponse(folderService.create(request));
    }

    @Operation(summary = "изменить группу")
    @PutMapping("/{id}")
    public FolderResponse update(@PathVariable UUID id, @RequestBody @Valid FolderRequest request) {
        return folderMapper.toResponse(folderService.update(id, request));
    }

    @Operation(summary = "показать вложенные группы")
    @GetMapping("/{id}/folders")
    public List<IncludedFolderResponse> showFolders(@PathVariable UUID id) {
        var includedFolders = folderService.showIncludedFolders(id);
        var folders = includedFolders.stream().toList();
        return includedFolderMapper.toResponse(folders);
    }

    @Operation(summary = "показать вложенные товары")
    @GetMapping("/{id}/products")
    public List<IncludedProductResponse> showProducts(@PathVariable UUID id) {
        var includedProducts = folderService.showIncludedProducts(id);
        var products = includedProducts.stream().toList();
        return includedProductMapper.toResponse(products);
    }

    @Operation(summary = "вложить группу")
    @PostMapping("/{id}/includeFolder")
    public void includeFolder(@PathVariable UUID id, @RequestBody IncludeItemRequest includeItemRequest) {
        folderService.includeFolder(id, includeItemRequest);
    }

    @Operation(summary = "вложить товар")
    @PostMapping("/{id}/includeProduct")
    public void includeProduct(@PathVariable UUID id, @RequestBody IncludeItemRequest includeItemRequest) {
        folderService.includeProduct(id, includeItemRequest);
    }

}
