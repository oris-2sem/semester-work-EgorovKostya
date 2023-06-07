package kpfu.itis.exception.validation;

import lombok.Getter;

@Getter
public class IllegalFileFormatException extends RuntimeException {

    private String message;
    public IllegalFileFormatException(String message) {
        this.message = message;
    }
}
