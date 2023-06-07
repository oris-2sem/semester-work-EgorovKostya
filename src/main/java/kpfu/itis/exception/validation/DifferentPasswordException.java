package kpfu.itis.exception.validation;

import lombok.Getter;

@Getter
public class DifferentPasswordException extends RuntimeException {

    private String username;

    public DifferentPasswordException(String username) {
        this.username = username;
    }
}
