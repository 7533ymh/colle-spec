package com.example.backend.advice.exception;

public class CLinkException extends RuntimeException{

    public CLinkException(String msg, Throwable t) {
        super(msg, t);
    }

    public CLinkException(String msg) {
        super(msg);
    }

    public CLinkException() {
        super();
    }

}