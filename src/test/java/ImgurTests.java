import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurTests {

    private static final Map<String, String> HEADERS = new HashMap<>();
    private static final String USER_NAME = "juliazorina181";
    private static final String TOKEN = "Bearer d37910524cb2dc4321f5a5ff892a9f82707a4f64";

    private static String uploadedImageId = null;

    @BeforeAll
    public static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        HEADERS.put("Authorization", TOKEN);

    }
    @Order(1)
    @Test
    public void getUserAccountTest () {
        JsonPath json = given()
                .headers(HEADERS)
                .expect()
                .body("data.url", is(USER_NAME))
                .body("data.id", is(notNullValue()))
                .when()
                .get("https://api.imgur.com/3/account/" + USER_NAME)
                .then()
                .statusCode(200)
                .body("success", is(true))
                .extract()
                .jsonPath();

        System.out.println(json.prettify());
    }

    @Order(2)
    @Test
    public void uploadImageTest () throws URISyntaxException {

        URI uri = ImgurTests.class.getResource("/01.jpg").toURI();
        File image = new File(uri);

        JsonPath response = given()
                .headers(HEADERS)
                .multiPart("image", image)
                .multiPart("name", "butterfly")
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .when()
                .post("https://api.imgur.com/3/upload")
                .then()
                .extract()
                .jsonPath();

        System.out.println(response.prettify());

        String imageId = response.getString("data.id");
        assertNotNull(imageId);
        String imageDeleteHash = response.getString("data.deletehash");
        assertNotNull(imageDeleteHash);
        String imageLink = response.getString("data.link");
        assertNotNull(imageLink);

        uploadedImageId = imageId;

    }

    @Order(3)
    @Test
    public void getImageByIdTest () {
        given()
                .headers(HEADERS)
                .expect()
                .statusCode(200)
                .when()
                .get("https://api.imgur.com/3/image/" + uploadedImageId)
                .then()
                .body("success", is(true))
                .body("data.id", is(uploadedImageId));
    }

    @Order(4)
    @Test
    public void uploadEmptyImageTest () {
        given()
                .headers(HEADERS)
                .expect()
                .statusCode(400)
                .when()
                .post("https://api.imgur.com/3/upload");
    }

    @Order(5)
    @Test
    public void getImageCountTest () {
        JsonPath json = given()
                .headers(HEADERS)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data", is(notNullValue()))
                .when()
                .get("https://api.imgur.com/3/account/" + USER_NAME + "/images/count")
                .jsonPath();

        System.out.println(json.prettify());
    }

    @Order(6)
    @Test
    public void getAllImagesTest () {
        given()
                .headers(HEADERS)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .get("https://api.imgur.com/3/account/me/images");
    }

    @Order(7)
    @Test
    public void getAllImagesWithoutHeadersTest () {
        given()
                .expect()
                .statusCode(401)
                .body("success", is(false))
                .body("data.error", is("Authentication required"))
                .when()
                .get("https://api.imgur.com/3/account/me/images");
    }

    @Order(8)
    @Test
    public void updateImageInformationTest () {
        JsonPath response = given()
                .headers(HEADERS)
                .multiPart("title", "Butterfly")
                .multiPart("description", "Very cute")
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .when()
                .post("https://api.imgur.com/3/image/" + uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();

    }

    @Order(9)
    @Test
    public void favoriteAnImageTest () {
        given()
                .headers(HEADERS)
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/image/" + uploadedImageId + "/favorite");
    }

    @Order(10)
    @Test
    public void unfavoriteAnImageTest () {
        given()
                .headers(HEADERS)
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/image/" + uploadedImageId + "/favorite");
    }

    @Order(11)
    @Test
    public void deleteImageTest () {
        given()
                .headers(HEADERS)
                .expect()
                .statusCode(200)
                .body("data", is(true))
                .body("success", is(true))
                .when()
                .delete("https://api.imgur.com/3/image/" + uploadedImageId);

    }

}
