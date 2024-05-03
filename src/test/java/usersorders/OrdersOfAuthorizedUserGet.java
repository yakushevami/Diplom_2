package usersorders;

import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientOrder;
import io.restassured.response.Response;
import useroperations.CreateAndDeleteUser;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersOfAuthorizedUserGet extends CreateAndDeleteUser {
    private final ClientOrder clientOrder = new ClientOrder();

    @Test
    @DisplayName("Get authorized user's orders test")
    public void getAuthorizedUsersOrdersTest() {
        Map<String, String[]> ingredients = new HashMap<>();
        ingredients.put("ingredients", new String[]{"61c0c5a71d1f82001bdaaa6d",
                                                    "61c0c5a71d1f82001bdaaa70",
                                                    "61c0c5a71d1f82001bdaaa73"});
        clientOrder.createOrderAuthUser(ingredients, accessToken);
        Response response = clientOrder.getAuthorizedUsersOrders(accessToken);
        response.then().statusCode(200).body("success", equalTo(true)).body("orders", notNullValue());
    }
}

