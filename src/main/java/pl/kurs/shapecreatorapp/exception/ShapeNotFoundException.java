package pl.kurs.shapecreatorapp.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShapeNotFoundException extends RuntimeException {
    private String type;
}
