package ingredients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Recipe {
    private final Random random = new Random();
    private final Map<String, String[]> ingredientsMap = new HashMap<>();
    List<Ingredient> ingredientList = new IngredientsList().getIngredients();

    public Map<String, String[]> getCorrectIngredients() {
        String[] ingredients = new String[3];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = ingredientList.get(random.nextInt(ingredientList.size())).get_id();
        }
        ingredientsMap.put("ingredients", ingredients);
        return ingredientsMap;
    }

    public Map<String, String[]> getIncorrectIngredients() {
        ingredientsMap.put("ingredients", new String[] {"12345"});
        return ingredientsMap;
    }
}