package com.example.backend.advice.exception;

import java.io.IOException;

public class CNotFoundEducationException extends RuntimeException {

    public CNotFoundEducationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundEducationException(String msg) {
        super(msg);
    }

    public CNotFoundEducationException() {
        super();
    }

}
