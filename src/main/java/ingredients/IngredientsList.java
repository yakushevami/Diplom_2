package ingredients;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class IngredientsList {
    private List<Ingredient> data;

    public IngredientsList(List<Ingredient> data) {
        this.data = data;
    }
}