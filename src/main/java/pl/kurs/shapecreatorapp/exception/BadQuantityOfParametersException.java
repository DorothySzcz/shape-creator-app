package pl.kurs.shapecreatorapp.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadQuantityOfParametersException extends RuntimeException {

    public BadQuantityOfParametersException(String message) {
        super(message);
    }
}
