package su.dedvano.goods.service

import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll
import su.dedvano.IntegrationTest
import su.dedvano.goods.domain.Product
import su.dedvano.goods.domain.ProductCategory
import su.dedvano.goods.dto.request.ProductRequest
import su.dedvano.goods.exception.ProductNotFoundException
import su.dedvano.goods.repository.ProductCategoryRepository
import su.dedvano.goods.repository.ProductRepository

class ProductServiceIT extends IntegrationTest {

    private static final String CATEGORY_NAME = "some category"

    private static final String FIRST_PRODUCT_NAME = "first product"
    private static final int FIRST_PRODUCT_PRICE = 10
    private static final boolean FIRST_PRODUCT_VARIABLE_PRICE = false
    private static final int FIRST_PRODUCT_COLOR = 1000

    private static final String SECOND_PRODUCT_NAME = "second product"
    private static final int SECOND_PRODUCT_PRICE = 20
    private static final boolean SECOND_PRODUCT_VARIABLE_PRICE = true
    private static final int SECOND_PRODUCT_COLOR = 2000

    @Autowired
    private ProductService sut

    @Autowired
    private ProductRepository productRepository

    @Autowired
    private ProductCategoryRepository categoryRepository

    def 'successfully create new product'() {
        given:
        def productName = "some product"
        def price = 20
        def variablePrice = false
        def categoryId = saveCategory(CATEGORY_NAME).id
        def color = 1000

        def request = new ProductRequest(
                productName,
                price,
                variablePrice,
                categoryId,
                color
        )
        when:
        def createdProduct = sut.create(request)

        then:
        createdProduct.name == productName
        createdProduct.price == price
        createdProduct.variablePrice == variablePrice
        createdProduct.category.id == categoryId
        createdProduct.color == color
        !createdProduct.deleted
    }

    def 'throw exception when try to create product with null request'() {
        when:
        sut.create(null)

        then:
        thrown IllegalArgumentException
    }

    def 'successfully update product'() {
        given:
        def category = saveCategory(CATEGORY_NAME)
        def product = saveProduct(
                FIRST_PRODUCT_NAME,
                FIRST_PRODUCT_PRICE,
                FIRST_PRODUCT_VARIABLE_PRICE,
                category,
                FIRST_PRODUCT_COLOR
        )
        def updatedName = "updated name"
        def updatedPrice = 20000
        def updatedVariablePrice = true
        def updatedColor = 500
        def request = new ProductRequest(
                updatedName,
                updatedPrice,
                updatedVariablePrice,
                category.id,
                updatedColor
        )

        when:
        def updatedProduct = sut.update(product.id, request)

        then:
        updatedProduct.id == product.id
        updatedProduct.name == updatedName
        updatedProduct.price == updatedPrice
        updatedProduct.variablePrice == updatedVariablePrice
        updatedProduct.category.id == category.id
        updatedProduct.color == updatedColor

    }

    def 'throw exception when try to update non-existing product'() {
        given:
        def category = saveCategory(CATEGORY_NAME)
        saveProduct(
                FIRST_PRODUCT_NAME,
                FIRST_PRODUCT_PRICE,
                FIRST_PRODUCT_VARIABLE_PRICE,
                category,
                FIRST_PRODUCT_COLOR
        )
        def updatedName = "updated name"
        def updatedPrice = 20000
        def updatedVariablePrice = true
        def updatedColor = 500
        def request = new ProductRequest(
                updatedName,
                updatedPrice,
                updatedVariablePrice,
                category.id,
                updatedColor
        )

        when:
        sut.update(UUID.randomUUID(), request)

        then:
        thrown ProductNotFoundException
    }

    @Unroll
    def 'throw exception when try to update product with null id or request'() {
        when:
        sut.update(id, request)

        then:
        thrown IllegalArgumentException

        where:
        id                | request
        null              | new ProductRequest("name", 2, false, UUID.randomUUID(), 20)
        UUID.randomUUID() | null
    }

    def 'successfully find product by id'() {
        given:
        def category = saveCategory(CATEGORY_NAME)
        def firstProduct = saveProduct(
                FIRST_PRODUCT_NAME,
                FIRST_PRODUCT_PRICE,
                FIRST_PRODUCT_VARIABLE_PRICE,
                category,
                FIRST_PRODUCT_COLOR
        )
        saveProduct(
                SECOND_PRODUCT_NAME,
                SECOND_PRODUCT_PRICE,
                SECOND_PRODUCT_VARIABLE_PRICE,
                category,
                SECOND_PRODUCT_COLOR
        )

        when:
        def retrievedProduct = sut.findById(firstProduct.id)

        then:
        retrievedProduct.name == FIRST_PRODUCT_NAME
        retrievedProduct.price == FIRST_PRODUCT_PRICE
        retrievedProduct.variablePrice == FIRST_PRODUCT_VARIABLE_PRICE
        retrievedProduct.category.id == category.id
        retrievedProduct.color == FIRST_PRODUCT_COLOR
    }

    def 'throw exception when try to find non-existing product'() {
        given:
        def category = saveCategory(CATEGORY_NAME)
        saveProduct(
                FIRST_PRODUCT_NAME,
                FIRST_PRODUCT_PRICE,
                FIRST_PRODUCT_VARIABLE_PRICE,
                category,
                FIRST_PRODUCT_COLOR
        )

        when:
        sut.findById(UUID.randomUUID())

        then:
        thrown ProductNotFoundException
    }

    def 'throw exception when try to find product with null id'() {
        when:
        sut.findById(null)

        then:
        thrown IllegalArgumentException
    }

    def setup() {

    }

    def cleanup() {
        productRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    def saveCategory(String name) {
        categoryRepository.save(new ProductCategory().setName(name))
    }

    def saveProduct(String name, int price, boolean variablePrice, ProductCategory category, int color) {
        productRepository.save(
                new Product()
                        .setName(name)
                        .setPrice(price)
                        .setVariablePrice(variablePrice)
                        .setCategory(category)
                        .setColor(color)
        )
    }
}
