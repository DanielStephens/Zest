package com.apakgroup.zest.exceptions;


public class TestFailureException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TestFailureException() {
        super();
    }

    public TestFailureException(final String message) {
        super(message);
    }

    public TestFailureException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TestFailureException(final Throwable cause) {
        super(cause);
    }
}

