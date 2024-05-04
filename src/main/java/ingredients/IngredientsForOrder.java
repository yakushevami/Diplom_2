package ingredients;

import client.ClientIngredients;

import java.util.ArrayList;

public class IngredientsForOrder {
    private final ClientIngredients clientIngredients = new ClientIngredients();

    public ArrayList<String> correctIngredientsForOrder() {
        IngredientsList ingredientsList = clientIngredients.getIngredients();
        ArrayList<String> ingredientsOrder = new ArrayList<>();
            ingredientsOrder.add(ingredientsList.getData().get(0).get_id());
            ingredientsOrder.add(ingredientsList.getData().get(1).get_id());
            ingredientsOrder.add(ingredientsList.getData().get(2).get_id());
        return ingredientsOrder;
    }

    public ArrayList<String> incorrectIngredientsForOrder() {
        IngredientsList ingredientsList = clientIngredients.getIngredients();
        ArrayList<String> ingredientsOrder = new ArrayList<>();
            ingredientsOrder.add(ingredientsList.getData().get(0).get_id() + "wrong hash");
        return ingredientsOrder;
    }
}
