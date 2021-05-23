package com.example.backend.domain.grade.exception;

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

