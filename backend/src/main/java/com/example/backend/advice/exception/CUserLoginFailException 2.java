package com.example.backend.advice.exception;

public class CUserLoginFailException extends RuntimeException{

    public CUserLoginFailException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserLoginFailException(String msg) {
        super(msg);
    }

    public CUserLoginFailException() {
        super();
    }

}
