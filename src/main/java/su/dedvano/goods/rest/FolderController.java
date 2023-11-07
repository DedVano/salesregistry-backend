package su.dedvano.goods.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.dedvano.goods.converter.FolderMapper;
import su.dedvano.goods.converter.IncludedFolderMapper;
import su.dedvano.goods.converter.IncludedProductMapper;
import su.dedvano.goods.dto.response.IncludedFolderResponse;
import su.dedvano.goods.dto.response.IncludedProductResponse;
import su.dedvano.goods.dto.response.FolderResponse;
import su.dedvano.goods.service.FolderService;

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

    @Operation(summary = "информация о группе")
    @GetMapping("/{id}")
    public FolderResponse getFolder(@PathVariable UUID id) {
        return folderMapper.toResponse(folderService.getById(id));
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

}
