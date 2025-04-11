package recipes.business.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import recipes.model.Recipe;

import java.util.List;

public record RecipeDTO(@NotBlank String name,
                        @NotBlank String description,
                        @NotBlank String category,
                        @NotNull @Size(min = 1) List<String> ingredients,
                        @NotNull @Size(min = 1) List<String> directions) {
    public RecipeDTO(Recipe recipe) {
        this(recipe.getName(), recipe.getDescription(), recipe.getCategory(),
                recipe.getIngredients(), recipe.getDirections());
    }

    public boolean checkDTO(Recipe recipe) {
        return name.equals(recipe.getName())
                && category.equals(recipe.getCategory())
                && description.equals(recipe.getDescription())
                && ingredients.equals(recipe.getIngredients())
                && directions.equals(recipe.getDirections());
    }
}
