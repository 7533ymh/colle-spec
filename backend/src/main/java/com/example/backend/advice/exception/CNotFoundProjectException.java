package com.example.backend.advice.exception;

public class CNotFoundProjectException extends RuntimeException {

    public CNotFoundProjectException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundProjectException(String msg) {
        super(msg);
    }

    public CNotFoundProjectException() {
        super();
    }

}
