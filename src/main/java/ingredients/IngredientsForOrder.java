package ingredients;

import client.ClientIngredients;

import java.util.ArrayList;
import java.util.Random;

public class IngredientsForOrder {
    private final ClientIngredients clientIngredients = new ClientIngredients();
    private final Random random = new Random();

    public ArrayList<String> correctIngredientsForOrder() {
        IngredientsList ingredientsList = clientIngredients.getIngredients();
        ArrayList<String> ingredientsOrder = new ArrayList<>();
            ingredientsOrder.add(ingredientsList.getData().get(random.nextInt(15)).get_id());
            ingredientsOrder.add(ingredientsList.getData().get(random.nextInt(15)).get_id());
            ingredientsOrder.add(ingredientsList.getData().get(random.nextInt(15)).get_id());
        return ingredientsOrder;
    }

    public ArrayList<String> incorrectIngredientsForOrder() {
        IngredientsList ingredientsList = clientIngredients.getIngredients();
        ArrayList<String> ingredientsOrder = new ArrayList<>();
            ingredientsOrder.add(ingredientsList.getData().get(random.nextInt(15)).get_id() + "wrong hash");
        return ingredientsOrder;
    }
}