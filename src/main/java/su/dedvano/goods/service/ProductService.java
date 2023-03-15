package su.dedvano.goods.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.dedvano.goods.domain.Product;
import su.dedvano.goods.dto.request.ProductRequest;
import su.dedvano.goods.exception.CategoryNotFoundException;
import su.dedvano.goods.exception.ProductNotFoundException;
import su.dedvano.goods.repository.ProductCategoryRepository;
import su.dedvano.goods.repository.ProductRepository;

import java.util.UUID;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product create(ProductRequest request) {
        return productRepository.save(setParams(new Product(), request));
    }

    public Product update(UUID id, ProductRequest request) {
        var product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productRepository.save(setParams(product, request));
    }

    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    private Product setParams(Product product, ProductRequest request) {
        return product
                .setName(request.name())
                .setPrice(request.price())
                .setVariablePrice(request.variablePrice())
                .setCategory(request.categoryId() == null ?
                        null :
                        categoryRepository
                                .findById(request.categoryId())
                                .orElseThrow(CategoryNotFoundException::new))
                .setColor(request.color());
    }

}
