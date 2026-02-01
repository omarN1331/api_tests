import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteTest {
    @Test(dependsOnMethods = {"GetTest.get_test"})
    public void delete_test() {
        given()
                .pathParam("petId", 11)
        .when()
                .delete("https://petstore.swagger.io/v2/pet/{petId}")
        .then()
                .statusCode(200)
                .body("message", Matchers.equalTo("11"));
    }
}
