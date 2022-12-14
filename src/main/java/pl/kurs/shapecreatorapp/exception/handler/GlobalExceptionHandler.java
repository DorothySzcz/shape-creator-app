package pl.kurs.shapecreatorapp.exception.handler;

import lombok.Builder;
import lombok.Value;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.exception.NoPermissionException;
import pl.kurs.shapecreatorapp.exception.ShapeNotFoundException;
import pl.kurs.shapecreatorapp.exception.constraints.ConstraintErrorHandler;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, ConstraintErrorHandler> constraintErrorMapper;

    public GlobalExceptionHandler(Set<ConstraintErrorHandler> handlers) {
        this.constraintErrorMapper = handlers.stream()
                .collect(Collectors.toMap(ConstraintErrorHandler::getConstraintName, Function.identity()));
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity handleNoPermissionException(NoPermissionException exc) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new NoPermissionExceptionDto("NO_PERMISSION_TO_RESOURCES"));
    }

    @ExceptionHandler(ShapeNotFoundException.class)
    public ResponseEntity handleShapeNotFoundException(ShapeNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ShapeNotFoundExceptionDto("SHAPE_NOT_FOUND"));
    }

    @ExceptionHandler(BadQuantityOfParametersException.class)
    public ResponseEntity handleBadQuantityOfParametersException(BadQuantityOfParametersException exc) {
        return ResponseEntity.badRequest().body(new BadQuantityOfParametersErrorDto("BAD_QUANTITY_OF_PARAMETERS"));

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException exc) {
        String constraintName = exc.getConstraintName().substring(8, exc.getConstraintName().indexOf(' ') - 8);
        return ResponseEntity.badRequest().body(constraintErrorMapper.get(constraintName).mapToErrorDto());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        return ResponseEntity.badRequest().body(
                exc.getFieldErrors().stream().map(
                        fe -> new ValidationErrorDto(fe.getDefaultMessage(), fe.getField())
                ).collect(Collectors.toList())
        );
    }

    @Value
    public static class NoPermissionExceptionDto {
        private String code;
    }

    @Value
    public static class ShapeNotFoundExceptionDto {
        private String code;
    }

    @Value
    public static class ValidationErrorDto {
        private String code;
        private String field;
    }

    @Value
    @Builder
    public static class BadQuantityOfParametersErrorDto {
        private String code;
    }

}
