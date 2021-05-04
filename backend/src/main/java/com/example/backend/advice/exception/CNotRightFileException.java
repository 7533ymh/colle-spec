package com.example.backend.advice.exception;

public class CNotRightFileException extends RuntimeException{

    public CNotRightFileException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotRightFileException(String msg) {
        super(msg);
    }

    public CNotRightFileException() {
        super();
    }


}
