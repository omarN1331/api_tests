import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetTest
{
    @Test(dependsOnMethods = {"PutTest.put_test"})
    public void get_test()
    {
        given()
                .pathParam("petId", 11)
        .when()
                .get("https://petstore.swagger.io/v2/pet/{petId}")
        .then()
                .statusCode(200)
                .log().body();
               /* .body("name", Matchers.equalTo("tessst")); */
    }
}
