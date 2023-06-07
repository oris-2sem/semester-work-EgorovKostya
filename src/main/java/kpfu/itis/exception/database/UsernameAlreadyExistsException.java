package kpfu.itis.exception.database;

import kpfu.itis.exception.MankakaServiceException;
import lombok.Getter;

@Getter
public class UsernameAlreadyExistsException extends AlreadyExistsException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
