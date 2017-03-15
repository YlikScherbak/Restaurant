package com.spring.exception;

public class DAOException extends RuntimeException {

    private String message;

    public DAOException(String massage) {
        this.message = massage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

