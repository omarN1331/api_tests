import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PostTest {

    @Test
    public void post_test() {
        File testUpload = new File("src/test/resources/4b6d8d02-4967-4aa6-b249-e9d497cacd83.png");
        given()
                .pathParam("petId", 11)
                .multiPart("file", testUpload, "image/png")
        .when()
                .post("https://petstore.swagger.io/v2/pet/{petId}/uploadImage")
        .then()
                .statusCode(200)
                .body("message", Matchers.equalTo("additionalMetadata: null\nFile uploaded to ./4b6d8d02-4967-4aa6-b249-e9d497cacd83.png, 309701 bytes"));
    }
}
