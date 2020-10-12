package be.abis.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= AgeValidator.class)
public @interface MinAge {
    String message() default "Age of person should be min 18";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
