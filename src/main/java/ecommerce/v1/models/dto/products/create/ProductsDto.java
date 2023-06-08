package ecommerce.v1.models.dto.products.create;
import ecommerce.v1.models.dto.products.create.valid.annotations.CategoriesFound;
import ecommerce.v1.models.dto.products.create.valid.annotations.SuppliersFound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
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
    @CategoriesFound
    private CategoriesDto category;
    @Getter @Setter
    @Valid
    @NotNull(message = "product suppliers is required")
    @SuppliersFound
    private SuppliersDto[] suppliers;
}
