package com.example.backend.advice.exception;

public class CNotFoundInfoByIdxException extends RuntimeException{

    public CNotFoundInfoByIdxException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundInfoByIdxException(String msg) {
        super(msg);
    }

    public CNotFoundInfoByIdxException() {
        super();
    }

}

