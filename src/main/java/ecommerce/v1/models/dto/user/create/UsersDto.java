package ecommerce.v1.models.dto.user.create;

import ecommerce.v1.models.entities.security.ROLE;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class UsersDto {
    @Getter @Setter
    @NotBlank(message = "user email is required")
    private String email;
    @Getter @Setter
    private ROLE role;


    @Getter @Setter
    @NotBlank(message = "user password is required")
    private String password;
}
