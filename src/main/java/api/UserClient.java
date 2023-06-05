package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {
    private static final String USER_CREATE = "api/auth/register";
    private static final String USER_DELETE = "api/auth/user";

    @Step("Создание пользователя")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_CREATE)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", "Bearer" + accessToken)
                .when()
                .delete(USER_DELETE)
                .then();
    }
}
