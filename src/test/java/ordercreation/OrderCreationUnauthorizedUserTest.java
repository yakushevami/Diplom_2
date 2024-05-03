package ordercreation;

import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientOrder;
import java.util.Map;
import java.util.HashMap;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderCreationUnauthorizedUserTest {
    public static final String EM_ID = "Ingredient ids must be provided";
    private final ClientOrder clientOrder = new ClientOrder();

    @Test
    @DisplayName("Create order with unauthorized user test")
    public void createOrderNoAuthUserTest() {
        Map<String, String[]> ingredients = new HashMap<>();
        ingredients.put("ingredients", new String[]{"61c0c5a71d1f82001bdaaa6d",
                "61c0c5a71d1f82001bdaaa70",
                "61c0c5a71d1f82001bdaaa73"});
        Response response = clientOrder.createOrderNoAuthUser(ingredients);
        response.then().statusCode(200).body("success", equalTo(true)).body("order", notNullValue());
    }

    @Test
    @DisplayName("Create order with unauthorized user without ingredients test")
    public void createOrderNoAuthUserNoIngredientsTest() {
        Map<String, String[]> ingredients = new HashMap<>();
        Response response = clientOrder.createOrderNoAuthUser(ingredients);
        response.then().statusCode(400).body("success", equalTo(false)).body("message", equalTo(EM_ID));
    }

    @Test
    @DisplayName("Create order with unauthorized user with wrong ingredient's hash test")
    public void createOrderNoAuthUserWrongHashTest() {
        Map<String, String[]> ingredients = new HashMap<>();
        ingredients.put("ingredients", new String[]{"12345"});
        Response response = clientOrder.createOrderNoAuthUser(ingredients);
        response.then().statusCode(500);
    }
}