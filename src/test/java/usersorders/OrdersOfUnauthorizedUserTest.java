package usersorders;

import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientOrder;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrdersOfUnauthorizedUserTest {
    private final ClientOrder clientOrder = new ClientOrder();
    public static final String EM_AUTHORIZED = "You should be authorised";

    @Test
    @DisplayName("Get unauthorized user's orders test")
    public void getUnauthorizedUsersOrdersTest() {
        Response response = clientOrder.getUnauthorizedUsersOrders();
        response.then().statusCode(401).body("success", equalTo(false)).body("message", equalTo(EM_AUTHORIZED));
    }
}
