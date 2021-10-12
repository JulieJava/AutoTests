package AutoTests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseTests {

    static Properties properties = new Properties();
    static String TOKEN;
    static String USER_NAME;

    protected static ResponseSpecification positiveResponseSpecification;
    protected static ResponseSpecification negativeResponseSpecification;
    protected static RequestSpecification requestSpecificationWithAuth;

    protected static final Map<String, String> HEADERS = new HashMap<>();

    protected static String uploadedImageId = null;
    protected static String uploadedImageDeleteHash = null;

    protected static String uploadedAlbumId = null;
    protected static String uploadedAlbumDeleteHash = null;

    @BeforeAll
    public static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = "https://api.imgur.com/3/";
        getProperties();

        TOKEN = properties.getProperty("TOKEN");
        USER_NAME = properties.getProperty("USER_NAME");

        HEADERS.put("Authorization", TOKEN);

        positiveResponseSpecification = new ResponseSpecBuilder()
                .expectBody("data", is(notNullValue()))
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(lessThanOrEqualTo(5000L))
                .build();

        negativeResponseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(false))
                .expectBody("data", is(notNullValue()))
                .expectStatusCode(anyOf(is(400), is(401)))
                .build();

        requestSpecificationWithAuth = new RequestSpecBuilder()
                .addHeader("Authorization", TOKEN)
                .build();

//        RestAssured.responseSpecification = positiveResponseSpecification;
    }

    private static void getProperties(){
        try (FileInputStream output = new FileInputStream("src/test/resources/application.properties")) {
            properties.load(output);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
