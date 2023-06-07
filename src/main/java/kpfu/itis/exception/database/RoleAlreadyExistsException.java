package kpfu.itis.exception.database;

import lombok.Getter;

@Getter
public class RoleAlreadyExistsException extends AlreadyExistsException {
    public RoleAlreadyExistsException(String message) {
        super(message);

    }
}
