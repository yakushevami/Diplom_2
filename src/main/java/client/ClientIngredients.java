package client;

import ingredients.IngredientsList;

public class ClientIngredients extends Client {
    private static final String INGREDIENTS = "/ingredients";

    public IngredientsList getIngredients() {
        return getSpec()
                .get(INGREDIENTS)
                .body()
                .as(IngredientsList.class);
    }
}