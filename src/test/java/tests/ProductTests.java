package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lesson5.dto.Product;
import lesson5.dto.ResponseFullError;
import lesson5.dto.ResponseError;
import lesson5.enums.CategoryType;
import lesson5.service.ProductService;
import lesson5.utils.RetrofitUtils;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTests {
    static Retrofit client;
    static ProductService productService;

    ObjectMapper mapper = new ObjectMapper();
    Faker faker = new Faker();

    private static Product savedProduct;

    @BeforeAll
    static void beforeAll () {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
    }

    @Order(1)
    @Test
    void postProductTest() throws IOException {
        Product product = new Product()
                .withTitle(faker.food().spice())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        savedProduct = response.body();
    }

    @Order(2)
    @Test
    void getProductByIdTest() throws  IOException {
        Response<Product> response = productService.getProduct(savedProduct.getId()).execute();
        assertThat(response.body().getTitle(), equalTo(savedProduct.getTitle()));
        assertThat(response.body().getPrice(), equalTo(savedProduct.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(savedProduct.getCategoryTitle()));
    }

    @Order(3)
    @Test
    void modifyProductTest() throws IOException {
        savedProduct.setTitle(faker.food().spice());
        savedProduct.setPrice(savedProduct.getPrice() + 10);
        Response<Product> response = productService.modifyProduct(savedProduct).execute();
        assertThat(response.body().getTitle(), equalTo(savedProduct.getTitle()));
        assertThat(response.body().getPrice(), equalTo(savedProduct.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(savedProduct.getCategoryTitle()));
    }

    @Order(4)
    @Test
    void deleteProductTest() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(savedProduct.getId()).execute();
        assertThat(response.code(), equalTo(200));
        assertThat(response.body().contentLength(), equalTo(0L));
    }

    @Order(5)
    @Test
    void negativeGetProductById() throws IOException {
        Response<Product> response = productService.getProduct(savedProduct.getId()).execute();
        assertThat(response.isSuccessful(), equalTo(false));
        ResponseError error = mapper.readValue(response.errorBody().string(), ResponseError.class);
        assertThat(error.getStatus(), equalTo(404));
        assertThat(error.getMessage(), equalTo("Unable to find product with id: " + savedProduct.getId()));
    }

    @Order(6)
    @Test
    void negativePostProductWithoutCategoryTitleTest() throws IOException {
        Product product = new Product()
                .withTitle(faker.food().spice())
                .withPrice((int) ((Math.random() + 1) * 100));
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.isSuccessful(), equalTo(false));
        ResponseFullError error = mapper.readValue(response.errorBody().string(), ResponseFullError.class);
        assertThat(error.getStatus(), equalTo(500));
        assertThat(error.getMessage(), equalTo(""));
        assertThat(error.getError(), equalTo("Internal Server Error"));
        assertThat(error.getPath(), equalTo("/market/api/v1/products"));
    }

    @Order(7)
    @Test
    void negativeModifyProductWithoutIdTest() throws IOException {
        savedProduct.setId(null);
        Response<Product> response = productService.modifyProduct(savedProduct).execute();
        assertThat(response.isSuccessful(), equalTo(false));
        ResponseError error = mapper.readValue(response.errorBody().string(), ResponseError.class);
        assertThat(error.getStatus(), equalTo(400));
        assertThat(error.getMessage(), equalTo("Id must be not null for new entity"));
    }
}
