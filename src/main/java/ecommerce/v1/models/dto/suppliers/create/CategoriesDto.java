package ecommerce.v1.models.dto.suppliers.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class CategoriesDto {
    @Getter @Setter
    @NotNull(message = "category id is required")
    private Long id;

    @Getter @Setter
    @NotBlank(message = "category name is required")
    private String name;
}
