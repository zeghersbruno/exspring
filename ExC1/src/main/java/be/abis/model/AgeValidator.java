package be.abis.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<MinAge, Integer> {

    public void initialize(MinAge constraint) {
    }

    @Override
    public boolean isValid(Integer ageMin, ConstraintValidatorContext context) {
        if (ageMin < 18) {
            return false;
        }
        return true;
    }
}
