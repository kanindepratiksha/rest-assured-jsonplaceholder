package base;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class Hooks {
    public static RequestSpecification reqSpec;

    @Before
    public void setUp() {
        RestAssured.baseURI = ConfigReader.get("base.url");
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.fromContentType(ConfigReader.get("content.type")))
                .build();
    }
}
