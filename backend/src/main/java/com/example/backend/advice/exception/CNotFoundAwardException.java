package com.example.backend.advice.exception;

public class CNotFoundAwardException extends RuntimeException{

    public CNotFoundAwardException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundAwardException(String msg) {
        super(msg);
    }

    public CNotFoundAwardException() {
        super();
    }


}
