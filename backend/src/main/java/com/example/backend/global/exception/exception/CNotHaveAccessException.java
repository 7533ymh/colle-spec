package com.example.backend.global.exception.exception;

public class CNotHaveAccessException extends RuntimeException {

    public CNotHaveAccessException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotHaveAccessException(String msg) {
        super(msg);
    }

    public CNotHaveAccessException() {
        super();
    }

}
