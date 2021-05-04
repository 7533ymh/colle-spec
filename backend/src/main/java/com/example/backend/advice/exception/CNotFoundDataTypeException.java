package com.example.backend.advice.exception;

import java.io.IOException;

public class CNotFoundDataTypeException extends IOException {

    public CNotFoundDataTypeException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundDataTypeException(String msg) {
        super(msg);
    }

    public CNotFoundDataTypeException() {
        super();
    }

}
