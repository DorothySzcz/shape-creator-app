package pl.kurs.shapecreatorapp.validation.impl;

import org.springframework.stereotype.Service;
import pl.kurs.shapecreatorapp.validation.annotation.NotNegative;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NotNegativeValidator implements ConstraintValidator<NotNegative, List<Double>> {

    @Override
    public boolean isValid(List<Double> parameters, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(parameters)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .allMatch(p -> p > 0);
    }
}
