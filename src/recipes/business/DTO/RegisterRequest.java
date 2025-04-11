package recipes.business.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@Email(regexp = ".+\\.\\w+") @NotBlank String email,
                              @NotBlank @Size(min = 8) String password) {
}
