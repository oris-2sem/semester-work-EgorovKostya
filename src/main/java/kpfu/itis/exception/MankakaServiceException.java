package kpfu.itis.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MankakaServiceException extends RuntimeException {

    private final HttpStatus status;

    public MankakaServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
