package recipes.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import recipes.model.YiaUser;

import java.util.Optional;

public interface YiaUserRepository extends JpaRepository<YiaUser, Long> {
    Optional<YiaUser> findByEmail(@Email @NotBlank String email);

    boolean existsByEmail(@Email @NotBlank String email);
}
