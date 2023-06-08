package ecommerce.v1.valids.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ecommerce.v1.valids.ENUMS.LETTER;

public class Letter implements ConstraintValidator<
        ecommerce.v1.valids.annotations.Letter,
        String> {

    private LETTER LETTER;
    @Override
    public void initialize(ecommerce.v1.valids.annotations.Letter constraintAnnotation) {
        this.LETTER = constraintAnnotation.mode();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        if (this.LETTER == this.LETTER.UPPERCASE && s.equals(s.toUpperCase()) == true){
            return true;
        } else if (this.LETTER == this.LETTER.LOWERCASE &&  s.equals(s.toLowerCase()) == true) {
            return true;
        }
        return false;
    }
}
