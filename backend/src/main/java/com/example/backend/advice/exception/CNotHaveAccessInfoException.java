package com.example.backend.advice.exception;

public class CNotHaveAccessInfoException extends RuntimeException{

    public CNotHaveAccessInfoException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotHaveAccessInfoException(String msg) {
        super(msg);
    }

    public CNotHaveAccessInfoException() {
        super();
    }

}

