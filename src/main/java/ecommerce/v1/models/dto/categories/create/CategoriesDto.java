package ecommerce.v1.models.dto.categories.create;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
public class CategoriesDto {
    @Getter @Setter
    @NotBlank(message = "category name is required")
    private String name;
}
