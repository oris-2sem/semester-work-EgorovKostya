package kpfu.itis.exception.database;

import kpfu.itis.exception.NotFoundException;
import lombok.Getter;



@Getter
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
