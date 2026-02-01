import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutTest {

    @Test(dependsOnMethods = {"PostTest.post_test"})
    public void put_test()
    {
        JSONObject petInfo = new JSONObject();
        petInfo.put("id", 11);
        petInfo.put("name", "tessst");

        JSONArray photoUrls = new JSONArray();
        photoUrls.add("string");

        JSONObject tagsObj = new JSONObject();
        tagsObj.put("id", 0);
        tagsObj.put("name", "string");

        JSONArray tags = new JSONArray();
        tags.add(tagsObj);

        JSONObject body = new JSONObject();
        body.put("id", 11);
        body.put("category", petInfo);
        body.put("name", "tessst");
        body.put("photoUrls", photoUrls);
        body.put("tags", tags);
        body.put("status", "available");

        given()
                .header("Content-Type", "application/json")
                .body(body.toJSONString())
        .when()
                .put("https://petstore.swagger.io/v2/pet")
        .then()
                .statusCode(200)
                .log().body()
                .body("name", Matchers.equalTo("tessst"));


    }
}
