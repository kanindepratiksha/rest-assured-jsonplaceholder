package steps;

import base.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsSteps {

    public static int postId;
    private Response response;

    @When("I create a post with title {string} and body {string} and userId {int}")
    public void create_post(String title, String body, int userId) {

        Map<String,Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("body", body);
        payload.put("userId", userId);

        response = given()
                .spec(Hooks.reqSpec)
                .body(payload)
                .when()
                .post("/posts");
    }

    @Then("I store the post id")
    public void store_post_id() {
        postId = response.jsonPath().getInt("id");
    }

    @When("I update the created post title to {string}")
    public void update_post(String newTitle) {

        Map<String,Object> payload = new HashMap<>();
        payload.put("title", newTitle);

        response = given()
                .spec(Hooks.reqSpec)
                .body(payload)
                .when()
                .patch("/posts/" + postId);
    }


    @When("I delete the created post")
    public void delete_post() {

        response = given()
                .spec(Hooks.reqSpec)
                .when()
                .delete("/posts/" + postId);
    }

    // ---- Assertions -----

    @Then("the post response status code should be {int}")
    public void verify_post_status(int status) {
        response.then().statusCode(status);
    }

    @Then("the response status code should be {int} or {int}")
    public void verify_multiple_status(int s1, int s2) {
        response.then().statusCode(anyOf(equalTo(s1), equalTo(s2)));
    }
}
