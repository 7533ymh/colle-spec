package com.example.backend.global.exception.exception;

public class CFindSameExamException extends RuntimeException{

    public CFindSameExamException(String msg, Throwable t) {
        super(msg, t);
    }

    public CFindSameExamException(String msg) {
        super(msg);
    }

    public CFindSameExamException() {
        super();
    }

}
