package usersorders;

import ingredients.Recipe;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientOrder;
import io.restassured.response.Response;
import useroperations.CreateAndDeleteUser;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersOfAuthorizedUserTest extends CreateAndDeleteUser {
    private final ClientOrder clientOrder = new ClientOrder();
    private final Recipe recipe = new Recipe();

    @Test
    @DisplayName("Get authorized user's orders test")
    public void getAuthorizedUsersOrdersTest() {
        Map<String, String[]> ingredients = recipe.getCorrectIngredients();
        clientOrder.createOrderAuthUser(ingredients, accessToken);
        Response response = clientOrder.getAuthorizedUsersOrders(accessToken);
        response.then().statusCode(200).body("success", equalTo(true)).body("orders", notNullValue());
    }
}