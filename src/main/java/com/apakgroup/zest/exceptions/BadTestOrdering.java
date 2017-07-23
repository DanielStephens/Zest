package com.apakgroup.zest.exceptions;


public class BadTestOrdering extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadTestOrdering() {
        super();
    }

    public BadTestOrdering(final String message) {
        super(message);
    }

    public BadTestOrdering(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BadTestOrdering(final Throwable cause) {
        super(cause);
    }
}

