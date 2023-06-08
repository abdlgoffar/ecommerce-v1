package ecommerce.v1.models.dto.products.create;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@EqualsAndHashCode
public class SuppliersDto {
    @Getter @Setter
    @NotNull(message = "supplier id is required")
    private Long id;
    @Getter @Setter
    @NotBlank(message = "supplier name is required")
    private String name;
    @Getter @Setter
    @NotBlank(message = "supplier email is required")
    @Email(message = "supplier email is invalid")
    private String email;
}
