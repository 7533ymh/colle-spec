package com.example.backend.domain.rank.exception;

public class CNotFoundRankException extends RuntimeException {

    public CNotFoundRankException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundRankException(String msg) {
        super(msg);
    }

    public CNotFoundRankException() {
        super();
    }
}