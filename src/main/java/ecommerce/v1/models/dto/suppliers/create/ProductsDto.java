package ecommerce.v1.models.dto.suppliers.create;

import ecommerce.v1.models.dto.suppliers.create.valid.annotations.CategoriesFound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


public class ProductsDto {
    @Getter @Setter
    @NotBlank(message = "product name is required")
    private String name;
    @Getter @Setter
    @NotNull(message = "product price is required")
    private Long price;
    @Getter @Setter
    @Valid
    @NotNull(message = "product category is required")
    @CategoriesFound(message = "there invalid category data")
    private CategoriesDto category;

}
