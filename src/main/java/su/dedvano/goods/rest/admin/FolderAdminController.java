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
import su.dedvano.goods.converter.FolderMapper;
import su.dedvano.goods.dto.request.FolderRequest;
import su.dedvano.goods.dto.request.IncludeItemRequest;
import su.dedvano.goods.dto.response.FolderResponse;
import su.dedvano.goods.service.FolderService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/folders")
@Tag(name = "Управление группами")
public class FolderAdminController {

    private final FolderService folderService;
    private final FolderMapper folderMapper;

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
