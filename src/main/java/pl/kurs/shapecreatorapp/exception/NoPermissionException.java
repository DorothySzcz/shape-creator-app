package pl.kurs.shapecreatorapp.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoPermissionException extends Exception {
        public NoPermissionException(String message) {
            super(message);
        }
}
