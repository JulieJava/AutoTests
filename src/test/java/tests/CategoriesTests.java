package tests;

import lesson5.dto.Category;
import lesson5.enums.CategoryType;
import lesson5.service.CategoryService;
import lesson5.utils.PrettyLogger;
import lesson5.utils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CategoriesTests {
    PrettyLogger prettyLogger = new PrettyLogger();

    static Retrofit client;
    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll () {
        client = RetrofitUtils.getRetrofit();
        categoryService = client.create(CategoryService.class);
    }

    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();
        prettyLogger.log(response.body().toString());
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }

}
