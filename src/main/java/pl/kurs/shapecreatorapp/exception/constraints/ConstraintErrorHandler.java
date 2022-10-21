package pl.kurs.shapecreatorapp.exception.constraints;

import pl.kurs.shapecreatorapp.exception.handler.GlobalExceptionHandler;

public interface ConstraintErrorHandler {
    GlobalExceptionHandler.ValidationErrorDto mapToErrorDto();

    String getConstraintName();
}
