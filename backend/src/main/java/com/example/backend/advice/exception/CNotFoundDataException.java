package com.example.backend.advice.exception;

public class CNotFoundDataException extends RuntimeException{

    public CNotFoundDataException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundDataException(String msg) {
        super(msg);
    }

    public CNotFoundDataException() {
        super();
    }

}