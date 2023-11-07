package su.dedvano.goods.service

import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll
import su.dedvano.IntegrationTest
import su.dedvano.goods.domain.Product
import su.dedvano.goods.domain.ProductCategory
import su.dedvano.goods.dto.request.ProductCategoryRequest
import su.dedvano.goods.exception.CategoryNotFoundException
import su.dedvano.goods.repository.ProductCategoryRepository
import su.dedvano.goods.repository.ProductRepository

class ProductCategoryServiceIT extends IntegrationTest {

    private static final String CATEGORY_NAME = "some category"
    private static final String CATEGORY_UPDATED_NAME = "updated category"

    private static final String FIRST_CATEGORY_NAME = "first category"
    private static final int FIRST_CATEGORY_ORDER = 5

    private static final String SECOND_CATEGORY_NAME = "second category"
    private static final int SECOND_CATEGORY_ORDER = 7

    private static final String FIRST_PRODUCT_NAME = "first product"
    private static final int FIRST_PRODUCT_PRICE = 10
    private static final boolean FIRST_PRODUCT_VARIABLE_PRICE = false
    private static final int FIRST_PRODUCT_COLOR = 1000

    private static final String SECOND_PRODUCT_NAME = "second product"
    private static final int SECOND_PRODUCT_PRICE = 20
    private static final boolean SECOND_PRODUCT_VARIABLE_PRICE = true
    private static final int SECOND_PRODUCT_COLOR = 2000

    @Autowired
    private ProductCategoryService sut

    @Autowired
    private ProductRepository productRepository

    @Autowired
    private ProductCategoryRepository categoryRepository

    def 'successfully create new category'() {
        given:
        def orderInReport = 1
        def request = new ProductCategoryRequest(CATEGORY_NAME, orderInReport)

        when:
        def createdCategory = sut.create(request)

        then:
        createdCategory.name == CATEGORY_NAME
        createdCategory.orderInReport == orderInReport
        !createdCategory.deleted
    }

    def 'throw exception when try to create category with null request'() {
        when:
        sut.create(null)

        then:
        thrown IllegalArgumentException
    }

    def 'successfully update category'() {
        given:
        def categoryOrder = 1
        def category = saveCategory(CATEGORY_NAME, categoryOrder)
        def categoryNewOrder = 2
        def updateRequest = new ProductCategoryRequest(CATEGORY_UPDATED_NAME, categoryNewOrder)

        when:
        def updatedCategory = sut.update(category.id, updateRequest)

        then:
        updatedCategory.id == category.id
        updatedCategory.name == CATEGORY_UPDATED_NAME
        updatedCategory.orderInReport == categoryNewOrder
    }

    def 'throw exception when try to update non-existing category'() {
        def categoryOrder = 1
        saveCategory(CATEGORY_NAME, categoryOrder)
        def categoryNewOrder = 3
        def request = new ProductCategoryRequest(CATEGORY_UPDATED_NAME, categoryNewOrder)

        when:
        sut.update(UUID.randomUUID(), request)

        then:
        thrown CategoryNotFoundException
    }

    @Unroll
    def 'throw exception when try to update category with null id or request'() {
        when:
        sut.update(id, request)

        then:
        thrown IllegalArgumentException

        where:
        id                | request
        null              | new ProductCategoryRequest("name", 1)
        UUID.randomUUID() | null
    }

    def 'successfully find category by id'() {
        given:
        def firstCategory = saveCategory(FIRST_CATEGORY_NAME, FIRST_CATEGORY_ORDER)
        saveCategory(SECOND_CATEGORY_NAME, SECOND_CATEGORY_ORDER)

        when:
        def retrievedCategory = sut.findById(firstCategory.id)

        then:
        retrievedCategory.id == firstCategory.id
        retrievedCategory.name == FIRST_CATEGORY_NAME
        retrievedCategory.orderInReport == FIRST_CATEGORY_ORDER
    }

    def 'throw exception when try to find non-existing category'() {
        saveCategory(FIRST_CATEGORY_NAME, FIRST_CATEGORY_ORDER)

        when:
        sut.findById(UUID.randomUUID())

        then:
        thrown CategoryNotFoundException
    }

    def 'throw exception when try to find category with null id'() {
        when:
        sut.findById(null)

        then:
        thrown IllegalArgumentException
    }

    def 'successfully find all categories'() {
        given:
        saveCategory(FIRST_CATEGORY_NAME, FIRST_CATEGORY_ORDER)
        saveCategory(SECOND_CATEGORY_NAME, SECOND_CATEGORY_ORDER)

        when:
        def categories = sut.findAll()

        then:
        categories.size() == 2
        categories[0].name == FIRST_CATEGORY_NAME
        categories[0].orderInReport == FIRST_CATEGORY_ORDER
        categories[1].name == SECOND_CATEGORY_NAME
        categories[1].orderInReport == SECOND_CATEGORY_ORDER
    }

    def 'successfully show products in category'() {
        given:
        def category = saveCategory(FIRST_CATEGORY_NAME, FIRST_CATEGORY_ORDER)
        saveProduct(FIRST_PRODUCT_NAME, FIRST_PRODUCT_PRICE, FIRST_PRODUCT_VARIABLE_PRICE, category, FIRST_PRODUCT_COLOR)
        saveProduct(SECOND_PRODUCT_NAME, SECOND_PRODUCT_PRICE, SECOND_PRODUCT_VARIABLE_PRICE, category, SECOND_PRODUCT_COLOR)

        when:
        def products = sut.showProducts(category.id)

        then:
        products.size() == 2
        products[0].name == FIRST_PRODUCT_NAME
        products[0].price == FIRST_PRODUCT_PRICE
        products[0].variablePrice == FIRST_PRODUCT_VARIABLE_PRICE
        products[0].color == FIRST_PRODUCT_COLOR
        products[0].category == category
        products[1].name == SECOND_PRODUCT_NAME
        products[1].price == SECOND_PRODUCT_PRICE
        products[1].variablePrice == SECOND_PRODUCT_VARIABLE_PRICE
        products[1].color == SECOND_PRODUCT_COLOR
        products[1].category == category
    }

    def 'throw exception when try to show products with null category id'() {
        when:
        sut.showProducts(null)

        then:
        thrown IllegalArgumentException
    }

    def setup() {

    }

    def cleanup() {
        productRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    def saveCategory(String name, int order) {
        categoryRepository.save(new ProductCategory().setName(name).setOrderInReport(order))
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
