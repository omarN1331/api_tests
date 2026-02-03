import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetTest
{
    @Test(dependsOnMethods = {"PutTest.put_test"})
    public void get_test()
    {
        given()
                .pathParam("petId", 15)
        .when()
                .get("https://petstore.swagger.io/v2/pet/{petId}")
        .then()
                .statusCode(200)
                .log().body()
                 .body("name", Matchers.equalTo("seadog"));
    }
    @Test
    public void get_test_2() {
        given()
                .param("status", "sold")
        .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
        .then()
                .statusCode(200)
                .body("name", Matchers.hasItem("teddy"));
    }
}
