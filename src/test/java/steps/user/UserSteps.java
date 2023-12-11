package steps.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static endpoints.UserUrl.*;
import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Create list of users with given input array" + USER_ARRAY)
    public static Response createUserList(RequestSpecification spec, String body) {
        return given().spec(spec)
                .body(body)
                .header("api_key", "special-key")
                .when().post(USER_ARRAY)
                .then().extract().response();
    }

    @Step("Get user by user name" + USER_ACTION)
    public static Response getUser(RequestSpecification spec) {
        return given().spec(spec)
                .when().get(USER_ACTION)
                .then().extract().response();
    }

    @Step("Update a user" + USER_ACTION)
    public static Response updateUser(RequestSpecification spec, String body) {
        return given().spec(spec)
                .body(body)
                .when().put(USER_ACTION)
                .then().extract().response();
    }

    @Step("Delete user by user name" + USER_ACTION)
    public static Response deleteUser(RequestSpecification spec) {
        return given().spec(spec)
                .when().delete(USER_ACTION)
                .then().extract().response();
    }

    @Step("Logs user into the system" + LOG_IN)
    public static Response logIn(RequestSpecification spec) {
        return given().spec(spec)
                .when().get(LOG_IN)
                .then().extract().response();
    }

    @Step("Logs out current logged in user session" + LOG_OUT)
    public static Response logOut(RequestSpecification spec) {
        return given().spec(spec)
                .when().get(LOG_OUT)
                .then().extract().response();
    }

    @Step("Create a user" + CREATE_USER)
    public static Response createUser(RequestSpecification spec, String body) {
        return given().spec(spec)
                .body(body)
                .when().post(CREATE_USER)
                .then().extract().response();
    }
}
