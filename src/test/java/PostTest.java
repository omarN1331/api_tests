import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;


import static io.restassured.RestAssured.*;

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

    @Test
    public void post_test_2() {

        JSONObject category = new JSONObject();
        category.put("id", 15);
        category.put("name", "seadog");

        JSONArray photoUrls = new JSONArray();
        photoUrls.add("string");

        JSONArray tags = new JSONArray();
        JSONObject tagObj = new JSONObject();
        tagObj.put("id", 15);
        tagObj.put("name", "seadog");
        tags.add(tagObj);

        JSONObject body = new JSONObject();
        body.put("id", 15);
        body.put("category", category);
        body.put("name", "seadog");
        body.put("photoUrls", photoUrls);
        body.put("tags", tags);
        body.put("status", "pending");

        given()
                .contentType("application/json")
                .body(body.toJSONString())
        .when()
                .post("https://petstore.swagger.io/v2/pet")
        .then()
                .statusCode(200)
                .log().body()
                .body("name", Matchers.equalTo("seadog"));
    }

    @Test
    public void post_test_3() {
        given()
                .pathParam("petId", 15)
                .param("name", "seadog")
                .param("status", "sold")
        .when()
                .post("https://petstore.swagger.io/v2/pet/{petId}")
        .then()
                .statusCode(200)
                .body("message", Matchers.equalTo("15"));
    }
}
