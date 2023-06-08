package ecommerce.v1.valids.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ecommerce.v1.valids.ENUMS.LETTER;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ecommerce.v1.valids.validators.Letter.class)//class validator
@Target({ElementType.FIELD, ElementType.METHOD})//target component can validator
@Retention(RetentionPolicy.RUNTIME)
public @interface Letter {
    LETTER mode();
    String message() default "invalid format letters";// set default failure message
    Class<?>[] groups() default {};// set default group
    Class<? extends Payload[]>[] payload() default {};// set default payload
}
