package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;

public class ClientOrder extends Client {
    public static final String AUTH_HEADER = "authorization";
    private static final String ORDERS = "/orders";

    @Step("Create order with authorized user")
    public Response createOrderAuthUser(Map<String, String[]> ingredients, String accessToken) {
        return getSpec()
                .header(AUTH_HEADER, accessToken)
                .body(ingredients)
                .post(ORDERS);
    }

    @Step("Create order with unauthorized user")
    public Response createOrderNoAuthUser(Map<String, String[]> ingredients) {
        return getSpec()
                .body(ingredients)
                .post(ORDERS);
    }

    @Step("Get authorized user's orders")
    public Response getAuthorizedUsersOrders(String accessToken) {
        return getSpec()
                .header(AUTH_HEADER, accessToken)
                .get(ORDERS);
    }

    @Step("Get unauthorized user's orders")
    public Response getUnauthorizedUsersOrders() {
        return getSpec()
                .get(ORDERS);
    }
}