package AutoTests;
import dto.GetImageByIdResponseDto;
import dto.PostImageResponseDto;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ImagesTests extends BaseTests {

    @Order(1)
    @Test
    public void uploadImageTest() throws URISyntaxException {

        URI uri = Objects.requireNonNull(ImagesTests.class.getResource("/01.jpg")).toURI();
        File image = new File(uri);

        PostImageResponseDto response = given()
                .spec(requestSpecificationWithAuth)
                .multiPart("image", image)
                .multiPart("name", "butterfly")
                .when()
                .post("/upload")
                .then()
                .spec(positiveResponseSpecification)
                .extract()
                .as(PostImageResponseDto.class);

        assertNotNull(response.getData().getId());
        assertNotNull(response.getData().getDeletehash());
        assertNotNull(response.getData().getLink());
        assertThat(response.getSuccess(), is(true));
        assertThat(response.getStatus(), equalTo(200));

        uploadedImageId = response.getData().getId();
        uploadedImageDeleteHash = response.getData().getDeletehash();
    }

    @Order(2)
    @Test
    public void getImageByIdTest() {
        GetImageByIdResponseDto response = given()
                .spec(requestSpecificationWithAuth)
                .when()
                .get("/image/" + uploadedImageId)
                .then()
                .body("data.id", is(uploadedImageId))
                .spec(positiveResponseSpecification)
                .extract()
                .as(GetImageByIdResponseDto.class);

        assertThat(response.getData().getId(), equalTo(uploadedImageId));
        assertNotNull(response.getData().getLink());
        assertThat(response.getSuccess(), is(true));
        assertThat(response.getStatus(), equalTo(200));

    }

    @Order(3)
    @Test
    public void uploadEmptyImageTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .post("/upload")
                .then()
                .spec(negativeResponseSpecification);
    }

    @Order(4)
    @Test
    public void getImageCountTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .get("/account/" + USER_NAME + "/images/count")
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(5)
    @Test
    public void getAllImagesTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .get("/account/me/images")
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(6)
    @Test
    public void getAllImagesWithoutHeadersTest() {
        given()
                .when()
                .get("/account/me/images")
                .then()
                .spec(negativeResponseSpecification);
    }

    @Order(7)
    @Test
    public void updateImageInformationTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .multiPart("title", "Butterfly")
                .multiPart("description", "Very cute")
                .when()
                .post("/image/" + uploadedImageId)
                .prettyPeek()
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(8)
    @Test
    public void favoriteAnImageTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .post("/image/" + uploadedImageId + "/favorite")
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(9)
    @Test
    public void unFavoriteAnImageTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .post("/image/" + uploadedImageId + "/favorite")
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(10)
    @Test
    public void deleteImageTest() {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .delete("/image/" + uploadedImageId)
                .then()
                .spec(positiveResponseSpecification);
    }

}
