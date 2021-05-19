package com.example.backend.global.exception.exception;

public class CNotFoundInfoByUserException extends RuntimeException{

    public CNotFoundInfoByUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundInfoByUserException(String msg) {
        super(msg);
    }

    public CNotFoundInfoByUserException() {
        super();
    }

}
