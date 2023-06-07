package kpfu.itis.exception.validation;

import lombok.Getter;

@Getter
public class IncorrectPasswordException extends RuntimeException {

    private String message;
    public IncorrectPasswordException(String message) {
        this.message = message;
    }
}
