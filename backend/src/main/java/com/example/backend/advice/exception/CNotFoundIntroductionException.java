package com.example.backend.advice.exception;

public class CNotFoundIntroductionException extends RuntimeException{

    public CNotFoundIntroductionException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundIntroductionException(String msg) {
        super(msg);
    }

    public CNotFoundIntroductionException() {
        super();
    }


}
