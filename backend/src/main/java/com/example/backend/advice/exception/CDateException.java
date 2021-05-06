package com.example.backend.advice.exception;

public class CDateException extends RuntimeException{

    public CDateException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDateException(String msg) {
        super(msg);
    }

    public CDateException() {
        super();
    }

}

