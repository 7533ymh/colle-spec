package com.example.backend.advice.exception;

public class CNotFoundExperienceException extends RuntimeException{

    public CNotFoundExperienceException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundExperienceException(String msg) {
        super(msg);
    }

    public CNotFoundExperienceException() {
        super();
    }

}
