package su.dedvano.goods.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import su.dedvano.goods.domain.Folder;
import su.dedvano.goods.domain.IncludedFolder;
import su.dedvano.goods.domain.IncludedProduct;
import su.dedvano.goods.dto.request.FolderRequest;
import su.dedvano.goods.dto.request.IncludeItemRequest;
import su.dedvano.goods.exception.FolderNotFoundException;
import su.dedvano.goods.repository.FolderRepository;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class FolderService {

    private final FolderRepository folderRepository;
    private final ProductService productService;

    public FolderService(FolderRepository folderRepository, ProductService productService) {
        this.folderRepository = folderRepository;
        this.productService = productService;
    }

    public Folder getById(UUID id) {
        Assert.notNull(id, "id must not be null");
        return folderRepository.findById(id).orElseThrow(FolderNotFoundException::new);
    }

    public Folder create(FolderRequest request) {
        Assert.notNull(request, "request must not be null");
        return folderRepository.save(setParams(new Folder(), request));
    }

    public Folder update(UUID id, FolderRequest request) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(request, "request must not be null");
        var folder = folderRepository.findById(id).orElseThrow(FolderNotFoundException::new);
        return folderRepository.save(setParams(folder, request));
    }

    public void includeFolder(UUID id, IncludeItemRequest includeRequest) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(includeRequest, "request must not be null");
        var folder = folderRepository.findById(id).orElseThrow(FolderNotFoundException::new);
        var includingFolder = folderRepository.findById(id).orElseThrow(FolderNotFoundException::new);
        folder.getIncludedFolders()
                .add(new IncludedFolder()
                        .setContainerFolder(folder)
                        .setFolder(includingFolder)
                        .setRow(includeRequest.row())
                        .setColumn(includeRequest.column())
                );
    }

    public void includeProduct(UUID id, IncludeItemRequest includeRequest) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(includeRequest, "request must not be null");
        var folder = folderRepository.findById(id).orElseThrow(FolderNotFoundException::new);
        var product = productService.findById(includeRequest.id());
        folder.getIncludedProducts()
                .add(new IncludedProduct()
                        .setContainerFolder(folder)
                        .setProduct(product)
                        .setRow(includeRequest.row())
                        .setColumn(includeRequest.column())
                );
    }

    public Set<IncludedFolder> showIncludedFolders(UUID id) {
        Assert.notNull(id, "id must not be null");
        return folderRepository.findById(id).orElseThrow(FolderNotFoundException::new).getIncludedFolders();
    }

    public Set<IncludedProduct> showIncludedProducts(UUID id) {
        Assert.notNull(id, "id must not be null");
        return folderRepository.findById(id).orElseThrow(FolderNotFoundException::new).getIncludedProducts();
    }

    private Folder setParams(Folder folder, FolderRequest request) {
        return folder
                .setName(request.name())
                .setSizeRows(request.sizeRows())
                .setSizeColumns(request.sizeColumns())
                .setColor(request.color());
    }

}
