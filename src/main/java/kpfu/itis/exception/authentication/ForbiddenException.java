package kpfu.itis.exception.authentication;

import kpfu.itis.exception.MankakaServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenException extends MankakaServiceException {

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
