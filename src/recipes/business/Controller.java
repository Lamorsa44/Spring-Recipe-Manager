package recipes.business;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.business.DTO.JustId;
import recipes.business.DTO.RecipeDTO;
import recipes.business.DTO.RecipeWithoutId;
import recipes.business.DTO.RegisterRequest;
import recipes.business.exception.YeaBadRequestException;

import java.util.List;

@RestController
public class Controller {

    private final RecipeService recipeService;
    private final YiaUserService yiaUserService;


    public Controller(RecipeService recipeService, YiaUserService yiaUserService) {
        this.recipeService = recipeService;
        this.yiaUserService = yiaUserService;
    }

    @GetMapping("api/recipe/{id}")
    public RecipeWithoutId getRecipe(@PathVariable(name = "id") long id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("api/recipe/search/")
    public List<RecipeWithoutId> searchRecipe(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "name", required = false) String name) {
        if ((category != null && name != null) || (category == null && name == null)) {
            throw new YeaBadRequestException("Category and name are mutually exclusive and should be at least one");
        } else if (category == null) {
            return recipeService.searchByName(name);
        } else {
            return recipeService.searchByCategory(category);
        }
    }

    @PostMapping("api/recipe/new")
    public JustId addRecipe(@Valid @RequestBody RecipeDTO recipe) {
        return recipeService.saveRecipe(recipe);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("api/recipe/{id}")
    public void updateRecipe(@PathVariable(name = "id") long id,
                             @Valid @RequestBody RecipeDTO recipe,
                             @AuthenticationPrincipal YiaUserAdapter adapter) {
        recipeService.updateRecipe(id, recipe, adapter);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("api/recipe/{id}")
    public void deleteRecipe(@PathVariable(name = "id") long id,
                             @AuthenticationPrincipal YiaUserAdapter adapter) {
        recipeService.deleteWithId(id, adapter);
    }

    // Auth

    @PostMapping("api/register")
    public void registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        yiaUserService.saveUser(registerRequest);
    }
}
