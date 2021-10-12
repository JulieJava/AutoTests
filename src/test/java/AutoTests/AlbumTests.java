package AutoTests;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class AlbumTests extends BaseTests{

    @Order(1)
    @Test
    public void uploadImageTest() throws URISyntaxException {

        URI uri = Objects.requireNonNull(ImagesTests.class.getResource("/01.jpg")).toURI();
        File image = new File(uri);

        JsonPath response = given()
                .spec(requestSpecificationWithAuth)
                .multiPart("image", image)
                .multiPart("name", "butterfly")
                .when()
                .post("/upload")
                .then()
                .spec(positiveResponseSpecification)
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
        uploadedImageDeleteHash = imageDeleteHash;
    }

    @Order(2)
    @Test
    public void AlbumCreationTest () {
        JsonPath albumResponse = given()
                .spec(requestSpecificationWithAuth)
                .multiPart("title", "My album")
                .multiPart("description", "First images")
                .multiPart("cover", uploadedImageId)
                .when()
                .post("/album")
                .then()
                .spec(positiveResponseSpecification)
                .extract()
                .jsonPath();

        String albumId = albumResponse.getString("data.id");
        assertNotNull(albumId);
        String albumDeleteHash = albumResponse.getString("data.deletehash");
        assertNotNull(albumDeleteHash);

        uploadedAlbumId = albumId;
        uploadedAlbumDeleteHash = albumDeleteHash;
    }

    @Order(3)
    @Test
    public void getAlbumTest () {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .get("/album/" + uploadedAlbumId)
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(4)
    @Test
    public void updateAlbumInfoTest () {
        given()
                .spec(requestSpecificationWithAuth)
                .multiPart("title", "First Images")
                .multiPart("description", "Hello World!")
                .when()
                .post("/album/" + uploadedAlbumId)
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(5)
    @Test
    public void addImagesToAnAlbumTest () {
        given()
                .spec(requestSpecificationWithAuth)
                .multiPart("deletehashes[]", uploadedImageDeleteHash)
                .when()
                .post("/album/" + uploadedAlbumDeleteHash + "/add")
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(6)
    @Test
    public void favoriteAlbumTest () {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .post("/album/" + uploadedAlbumId + "/favorite")
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(7)
    @Test
    public void removeImagesFromAnAlbumTest () {
        given()
                .spec(requestSpecificationWithAuth)
                .multiPart("ids[]", uploadedImageId)
                .when()
                .post("/album/" + uploadedAlbumDeleteHash + "/remove_images")
                .then()
                .spec(positiveResponseSpecification);
    }

    @Order(8)
    @Test
    public void deleteAlbumTest () {
        given()
                .spec(requestSpecificationWithAuth)
                .when()
                .delete("/album/" + uploadedAlbumId)
                .then()
                .spec(positiveResponseSpecification);
    }

}
