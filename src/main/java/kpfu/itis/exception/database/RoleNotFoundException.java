package kpfu.itis.exception.database;

import kpfu.itis.exception.NotFoundException;
import lombok.Getter;

@Getter
public class RoleNotFoundException extends NotFoundException {

    public RoleNotFoundException(String message) {
        super(message);
    }
}
