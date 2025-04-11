package recipes.business;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import recipes.business.DTO.JustId;
import recipes.business.DTO.RecipeDTO;
import recipes.business.DTO.RecipeWithoutId;
import recipes.business.exception.YeaBadRequestException;
import recipes.business.exception.YeaForBiddenException;
import recipes.business.exception.YeaNotFoundException;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeWithoutId getRecipe(long id) {
        return new RecipeWithoutId(recipeRepository.findById(id)
                .orElseThrow(YeaNotFoundException::new));
    }

    public JustId saveRecipe(RecipeDTO recipe) {
        if (recipeRepository.findAll().stream().anyMatch(r -> r.checkDTO(recipe))) {
            throw new YeaBadRequestException("Recipe already exists");
        }
        return new JustId(recipeRepository.save(new Recipe(recipe)).getId());
    }

    public void deleteWithId(long id, YiaUserAdapter adapter) {
        var recipe = recipeRepository.findById(id).orElseThrow(YeaNotFoundException::new);
        if (!recipe.getAuthor().equals(adapter.getUsername())) {
            throw new YeaForBiddenException("Recipe doesn't belong to you");
        }

        recipeRepository.deleteById(id);
    }

    public List<RecipeWithoutId> searchByName(String name) {
        return recipeRepository.searchByNameContainingIgnoreCaseOrderByDateDesc(name)
                .stream().map(RecipeWithoutId::new).toList();
    }

    public List<RecipeWithoutId> searchByCategory(String category) {
        return recipeRepository.findByCategoryLikeIgnoreCaseOrderByDateDesc(category)
                .stream().map(RecipeWithoutId::new).toList();
    }

    public void updateRecipe(long id, @Valid RecipeDTO recipe, YiaUserAdapter adapter) {
        var yea = recipeRepository.findById(id).orElseThrow(YeaNotFoundException::new);
        if (!yea.getAuthor().equals(adapter.getUsername())) {
            throw new YeaForBiddenException("Recipe doesn't belong to you");
        }
        yea.update(recipe);
        recipeRepository.save(yea);
    }
}
