package recipes.business.DTO;

import recipes.model.Recipe;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record RecipeWithoutId(String name, String category, String date, String description,
                              List<String> ingredients, List<String> directions) {
    public RecipeWithoutId(Recipe recipe) {
        this(recipe.getName(), recipe.getCategory(),
                recipe.getDate().format(DateTimeFormatter.ISO_DATE_TIME),
                recipe.getDescription(), recipe.getIngredients(), recipe.getDirections());
    }
}
