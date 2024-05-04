package usersorders;

import ingredients.IngredientsForOrder;
import ingredients.Order;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientOrder;
import io.restassured.response.Response;
import useroperations.CreateAndDeleteUser;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersOfAuthorizedUserTest extends CreateAndDeleteUser {
    private final ClientOrder clientOrder = new ClientOrder();
    private final IngredientsForOrder ingredientsForOrder = new IngredientsForOrder();

    @Test
    @DisplayName("Get authorized user's orders test")
    public void getAuthorizedUsersOrdersTest() {
        ArrayList<String> ingredientsOrder = ingredientsForOrder.correctIngredientsForOrder();
        Order order = new Order(ingredientsOrder);
        clientOrder.createOrderAuthUser(order, accessToken);
        Response responseOrders = clientOrder.getAuthorizedUsersOrders(accessToken);
        responseOrders.then().statusCode(200).body("success", equalTo(true)).body("orders", notNullValue());
    }
}