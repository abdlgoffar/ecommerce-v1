package ecommerce.v1.models.dto.products.create.valid.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ecommerce.v1.models.dto.products.create.valid.validators.CategoriesFound.class)//class validator
@Target({ElementType.FIELD, ElementType.METHOD})//target component can validator
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoriesFound {
    String message() default "invalid categories data";// set default failure message

    Class<?>[] groups() default {};// set default group

    Class<? extends Payload[]>[] payload() default {};// set default payload
}
