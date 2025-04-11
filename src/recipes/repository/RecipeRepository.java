package recipes.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> searchByNameContainingIgnoreCaseOrderByDateDesc(@NotBlank String name);

    List<Recipe> findByCategoryContainingIgnoreCaseOrderByDateDesc(@NotBlank String category);

    List<Recipe> findByCategoryLikeIgnoreCaseOrderByDateDesc(@NotBlank String category);
}
