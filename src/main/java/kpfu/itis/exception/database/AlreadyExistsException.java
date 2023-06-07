package kpfu.itis.exception.database;

import kpfu.itis.exception.MankakaServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyExistsException extends MankakaServiceException {
    public AlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
