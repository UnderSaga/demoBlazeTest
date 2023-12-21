package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import models.requestModels.AuthRegRequest;
import models.responseModels.AuthRegResponse;

import static io.restassured.RestAssured.given;

public class API_Steps {

    @Step("Создание пользователя (провал)")
    public AuthRegResponse userRegistration(String baseURL, AuthRegRequest authRegRequest, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authRegRequest)
                .when()
                .post("/signup")
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract().body().as(AuthRegResponse.class);
    }

    @Step("Создание пользователя")
    public String userRegSuccessful(String baseURL, AuthRegRequest authRegRequest, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authRegRequest)
                .when()
                .post("/signup")
                .then()
                .statusCode(statusCode)
                .extract().body().asString();
    }

    @Step("Авторизация пользователя (провал)")
    public String userAuthorization(String baseURL, AuthRegRequest authRegRequest, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authRegRequest)
                .when()
                .post("/login")
                .then()
                .statusCode(statusCode)
                .extract().body().asString();
    }

    @Step("Авторизация пользователя")
    public AuthRegResponse userAuthorizationSuccessful(String baseURL, AuthRegRequest authRegRequest, Integer statusCode){
        return given()
                .baseUri(baseURL)
                .contentType("application/json")
                .body(authRegRequest)
                .when()
                .post("/login")
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract().body().as(AuthRegResponse.class);
    }
}
