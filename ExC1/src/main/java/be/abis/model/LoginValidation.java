package be.abis.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= LoginValidator.class)
public @interface LoginValidation {
    String message() default "Make sure your email and password are both correct";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
