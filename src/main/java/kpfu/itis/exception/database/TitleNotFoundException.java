package kpfu.itis.exception.database;

import lombok.Getter;

@Getter
public class TitleNotFoundException extends RuntimeException {

    private String message;

    public TitleNotFoundException(String message) {
        this.message = message;
    }
}
