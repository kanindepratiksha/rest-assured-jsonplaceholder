package steps;

import base.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersSteps {

    private Response response;

    @When("I send GET request to {string}")
    public void i_send_get_request(String endpoint) {
        response = given()
                .spec(Hooks.reqSpec)
                .when()
                .get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void verify_status(int status) {
        response.then().statusCode(status);
    }

    @Then("the response should contain at least 1 user")
    public void verify_users_count() {
        response.then().body("size()", greaterThan(0));
    }

    @Then("the field {string} should be {int}")
    public void verify_field(String field, int value) {
        response.then().body(field, equalTo(value));
    }
}
