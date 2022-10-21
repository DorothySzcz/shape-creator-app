package pl.kurs.shapecreatorapp.validation.annotation;

import pl.kurs.shapecreatorapp.validation.impl.NotNegativeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNegativeValidator.class)
public @interface NotNegative {
    String message() default "PARAMETERS_NOT_POSITIVE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
