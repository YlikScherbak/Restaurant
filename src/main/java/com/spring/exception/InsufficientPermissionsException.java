package com.spring.exception;

public class InsufficientPermissionsException extends RuntimeException{

    private String message;

    public InsufficientPermissionsException(String massage) {
        this.message = massage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
