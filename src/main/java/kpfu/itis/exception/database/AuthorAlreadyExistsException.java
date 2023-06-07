package kpfu.itis.exception.database;

import lombok.Getter;

@Getter
public class AuthorAlreadyExistsException extends AlreadyExistsException {

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
