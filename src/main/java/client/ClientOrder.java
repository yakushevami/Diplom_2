package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ingredients.Order;

public class ClientOrder extends Client {
    public static final String AUTH_HEADER = "authorization";
    private static final String ORDERS = "/orders";

    @Step("Create order with authorized user")
    public Response createOrderAuthUser(Order order, String accessToken) {
        return getSpec()
                .header(AUTH_HEADER, accessToken)
                .body(order)
                .post(ORDERS);
    }

    @Step("Create order with unauthorized user")
    public Response createOrderNoAuthUser(Order order) {
        return getSpec()
                .body(order)
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