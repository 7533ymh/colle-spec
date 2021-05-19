package com.example.backend.global.exception.exception;

public class CFindSameGradeException extends RuntimeException{

    public CFindSameGradeException(String msg, Throwable t) {
        super(msg, t);
    }

    public CFindSameGradeException(String msg) {
        super(msg);
    }

    public CFindSameGradeException() {
        super();
    }

}

