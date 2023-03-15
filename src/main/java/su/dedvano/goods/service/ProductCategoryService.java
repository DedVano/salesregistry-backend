package su.dedvano.goods.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.dedvano.goods.domain.Product;
import su.dedvano.goods.domain.ProductCategory;
import su.dedvano.goods.dto.request.ProductCategoryRequest;
import su.dedvano.goods.exception.CategoryNotFoundException;
import su.dedvano.goods.repository.ProductCategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    public ProductCategoryService(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ProductCategory> findAll() {
        return categoryRepository.findAllByDeletedFalse();
    }

    public ProductCategory findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public ProductCategory create(ProductCategoryRequest request) {
        return categoryRepository.save(setParams(new ProductCategory(), request));
    }

    public ProductCategory update(UUID id, ProductCategoryRequest request) {
        return categoryRepository.save(setParams(findById(id), request));
    }

    public List<Product> showProducts(UUID id) {
        return findById(id).getProducts();
    }

    private ProductCategory setParams(ProductCategory category, ProductCategoryRequest request) {
        return category
                .setName(request.name())
                .setOrderInReport(request.orderInReport());
    }

}
