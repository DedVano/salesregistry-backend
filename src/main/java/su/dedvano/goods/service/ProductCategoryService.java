package su.dedvano.goods.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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

    public ProductCategory create(ProductCategoryRequest request) {
        Assert.notNull(request, "request must not be null");
        return categoryRepository.save(setParams(new ProductCategory(), request));
    }

    public ProductCategory update(UUID id, ProductCategoryRequest request) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(request, "request must not be null");
        return categoryRepository.save(setParams(findById(id), request));
    }

    @Transactional(readOnly = true)
    public List<ProductCategory> findAll() {
        return categoryRepository.findAllByDeletedFalse();
    }

    @Transactional(readOnly = true)
    public ProductCategory findById(UUID id) {
        Assert.notNull(id, "id must not be null");
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Product> showProducts(UUID id) {
        var products = findById(id).getProducts();
        Hibernate.initialize(products);
        return products;
    }

    private ProductCategory setParams(ProductCategory category, ProductCategoryRequest request) {
        return category
                .setName(request.name())
                .setOrderInReport(request.orderInReport());
    }

}
