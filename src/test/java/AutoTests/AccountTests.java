package AutoTests;

import dto.GetAccountResponseDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.given;

public class AccountTests extends BaseTests {

    @Order(1)
    @Test
    public void getUserAccountTest() {
        GetAccountResponseDto response = given()
                .spec(requestSpecificationWithAuth)
                .pathParam("id", USER_NAME)
                .when()
                .get("/account/{id}")
                .then()
                .spec(positiveResponseSpecification)
                .extract()
                .as(GetAccountResponseDto.class);

        assertNotNull(response.getData().getId());
        assertNotNull(response.getData().getUrl());
        assertThat(response.getSuccess(), is(true));
        assertThat(response.getStatus(), equalTo(200));



    }

}
