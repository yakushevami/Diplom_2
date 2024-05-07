package ordercreation;

import ingredients.IngredientsForOrder;
import ingredients.Order;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientOrder;
import useroperations.CreateAndDeleteUser;
import java.util.ArrayList;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderCreationAuthorizedUserTest extends CreateAndDeleteUser {
    public static final String EM_ID = "Ingredient ids must be provided";
    private final ClientOrder clientOrder = new ClientOrder();
    private final IngredientsForOrder ingredientsForOrder = new IngredientsForOrder();

    @Test
    @DisplayName("Create order with authorized user test")
    public void createOrderAuthUserTest() {
        ArrayList<String> ingredientsOrder = ingredientsForOrder.correctIngredientsForOrder();
        Order order = new Order(ingredientsOrder);
        Response response = clientOrder.createOrderAuthUser(order, accessToken);
        response.then().statusCode(200).body("success", equalTo(true)).body("order", notNullValue());
    }

    @Test
    @DisplayName("Create order with authorized user without ingredients test")
    public void createOrderAuthUserNoIngredientsTest() {
        ArrayList<String> ingredientsOrder = new ArrayList<>();
        Order order = new Order(ingredientsOrder);
        Response response = clientOrder.createOrderAuthUser(order,accessToken);
        response.then().statusCode(400).body("success", equalTo(false)).body("message", equalTo(EM_ID));
    }

    @Test
    @DisplayName("Create order with authorized user with wrong ingredient's hash test")
    public void createOrderAuthUserWrongHashTest() {
        ArrayList<String> ingredientsOrder = ingredientsForOrder.incorrectIngredientsForOrder();
        Order order = new Order(ingredientsOrder);
        Response response = clientOrder.createOrderAuthUser(order, accessToken);
        response.then().statusCode(500);
    }
}