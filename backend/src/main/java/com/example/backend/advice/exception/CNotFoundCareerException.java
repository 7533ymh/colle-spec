package com.example.backend.advice.exception;

public class CNotFoundCareerException extends RuntimeException{

    public CNotFoundCareerException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundCareerException(String msg) {
        super(msg);
    }

    public CNotFoundCareerException() {
        super();
    }


}
