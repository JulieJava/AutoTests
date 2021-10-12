package AutoTests;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AccountTests extends BaseTests {

    @Order(1)
    @Test
    public void getUserAccountTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .pathParam("id", USER_NAME)
                .when()
                .get("/account/{id}")
                .then()
                .spec(positiveResponseSpecification);

    }

}
