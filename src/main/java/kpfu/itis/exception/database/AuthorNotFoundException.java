package kpfu.itis.exception.database;

import kpfu.itis.exception.NotFoundException;
import lombok.Getter;

@Getter
public class AuthorNotFoundException extends NotFoundException {


    public AuthorNotFoundException(String message) {
        super(message);

    }
}
