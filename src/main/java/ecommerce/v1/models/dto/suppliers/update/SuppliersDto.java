package ecommerce.v1.models.dto.suppliers.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class SuppliersDto {
    @Getter @Setter
    @NotBlank(message = "supplier name is required")
    private String name;
    @Getter @Setter
    @NotBlank(message = "supplier email is required")
    @Email(message = "supplier email is invalid")
    private String email;
}
