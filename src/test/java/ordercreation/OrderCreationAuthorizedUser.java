package ordercreation;

import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientOrder;
import useroperations.CreateAndDeleteUser;
import java.util.Map;
import java.util.HashMap;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderCreationAuthorizedUser extends CreateAndDeleteUser {
    public static final String EM_ID = "Ingredient ids must be provided";
    private final ClientOrder clientOrder = new ClientOrder();

    @Test
    @DisplayName("Create order with authorized user test")
    public void createOrderAuthUserTest() {
        Map<String, String[]> ingredients = new HashMap<>();
            ingredients.put("ingredients", new String[]{"61c0c5a71d1f82001bdaaa6d",
                                                        "61c0c5a71d1f82001bdaaa70",
                                                        "61c0c5a71d1f82001bdaaa73"});
        Response response = clientOrder.createOrderAuthUser(ingredients, accessToken);
        response.then().statusCode(200).body("success", equalTo(true)).body("order", notNullValue());
    }

    @Test
    @DisplayName("Create order with authorized user without ingredients test")
    public void createOrderAuthUserNoIngredientsTest() {
        Map<String, String[]> ingredients = new HashMap<>();
        Response response = clientOrder.createOrderAuthUser(ingredients, accessToken);
        response.then().statusCode(400).body("success", equalTo(false)).body("message", equalTo(EM_ID));
    }

    @Test
    @DisplayName("Create order with authorized user with wrong ingredient's hash test")
    public void createOrderAuthUserWrongHashTest() {
        Map<String, String[]> ingredients = new HashMap<>();
            ingredients.put("ingredients", new String[]{"12345"});
        Response response = clientOrder.createOrderAuthUser(ingredients, accessToken);
        response.then().statusCode(500);
    }
}