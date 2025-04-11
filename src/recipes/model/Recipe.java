package recipes.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import recipes.business.DTO.RecipeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
public class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String category;

    private LocalDateTime date;

    @NotBlank
    private String description;
    @ElementCollection @Size(min = 1)
    private List<String> ingredients;
    @ElementCollection @Size(min = 1)
    private List<String> directions;

    @CreatedBy
    private String author;

    public Recipe(RecipeDTO recipe) {
        this(recipe.name(), recipe.category(), recipe.description(), recipe.ingredients(), recipe.directions());
    }

    public Recipe(String name, String category, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        date = LocalDateTime.now();
    }

    public boolean checkDTO(RecipeDTO recipeDTO) {
        return recipeDTO.name().equals(getName())
                && recipeDTO.category().equals(getCategory())
                && recipeDTO.description().equals(getDescription())
                && recipeDTO.ingredients().equals(getIngredients())
                && recipeDTO.directions().equals(getDirections());
    }

    public void update(@Valid RecipeDTO recipe) {
        setName(recipe.name());
        setCategory(recipe.category());
        setDescription(recipe.description());
        setIngredients(recipe.ingredients());
        setDirections(recipe.directions());
        setDate(LocalDateTime.now());
    }
}

