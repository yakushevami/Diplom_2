package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.util.Map;
import user.User;
import user.UserWithoutEmail;
import user.UserWithoutName;
import user.UserWithoutPassword;

public class ClientUser extends Client {
    private static final String AUTH = "/auth";
    private static final String REGISTER = "/register";
    private static final String USER = "/user";
    private static final String LOGIN = "/login";
    public static final String AUTH_HEADER = "authorization";

    @Step("Register valid user")
    public Response registerValidUser(User user) {
        return getSpec()
                .body(user)
                .when()
                .post(AUTH + REGISTER);
    }

    @Step("Register user without name value")
    public ValidatableResponse registerUserWithoutName(UserWithoutName userWithoutName) {
        return getSpec()
                .body(userWithoutName)
                .post(AUTH + REGISTER)
                .then();
    }

    @Step("Register user without email value")
    public ValidatableResponse registerUserWithoutEmail(UserWithoutEmail userWithoutEmail) {
        return getSpec()
                .body(userWithoutEmail)
                .post(AUTH + REGISTER)
                .then();
    }

    @Step("Register user without password value")
    public ValidatableResponse registerUserWithoutPassword(UserWithoutPassword userWithoutPassword) {
        return getSpec()
                .body(userWithoutPassword)
                .post(AUTH + REGISTER)
                .then();
    }

    @Step("Authorize valid user")
    public Response authorizeValidUser(User user) {
        return getSpec()
                .body(user)
                .post(AUTH + LOGIN);
    }

    @Step("Authorize user without email value")
    public ValidatableResponse authorizeUserWithoutEmail(UserWithoutEmail userWithoutEmail) {
        return getSpec()
                .body(userWithoutEmail)
                .post(AUTH + LOGIN)
                .then();
    }

    @Step("Authorize user without password value")
    public ValidatableResponse authorizeUserWithoutPassword(UserWithoutPassword userWithoutPassword) {
        return getSpec()
                .body(userWithoutPassword)
                .post(AUTH + LOGIN)
                .then();
    }

    @Step("Update authorized user")
    public Response updateAuthorizedUser(Map<String, String> updateData, String accessToken) {
        return getSpec()
                .header(AUTH_HEADER, accessToken)
                .body(updateData)
                .patch(AUTH + USER);
    }

    @Step("Update unauthorized user")
    public Response updateUnauthorizedUser(Map<String, String> updateData) {
        return getSpec()
                .body(updateData)
                .patch(AUTH + USER);
    }

    @Step("Delete user")
    public void deleteUser(User user, String accessToken) {
        getSpec()
                .header(AUTH_HEADER, accessToken)
                .body(user)
                .delete(AUTH + USER);
    }
}