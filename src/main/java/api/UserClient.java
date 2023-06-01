package api;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {
    private static final String USER_CREATE = "api/auth/register";
    private static final String USER_DELETE = "api/auth/user";
    public ValidatableResponse create(User user){
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_CREATE)
                .then();
    }
    public ValidatableResponse delete(String accessToken){
        return given()
                .spec(getBaseSpec())
                .header("Authorization", "Bearer" + accessToken)
                .when()
                .delete(USER_DELETE)
                .then();
    }
}
