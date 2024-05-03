package client;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class Client {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String BASE_PATH = "/api";

    public RequestSpecification getSpec() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }
}