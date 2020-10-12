package be.abis.model;

import be.abis.service.AbisTrainingService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginValidator implements ConstraintValidator<LoginValidation, Login> {

    @Autowired
    AbisTrainingService trainingService;

    public void initialize(LoginValidation constraint) {
    }

    @Override
    public boolean isValid(Login login, ConstraintValidatorContext context) {
        Person person = trainingService.findPerson(login.getEmail(), login.getPassword());
        if (person==null) return true;
        return false;
    }
}
