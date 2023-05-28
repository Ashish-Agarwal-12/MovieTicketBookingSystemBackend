package com.multiplex.ticketBooking.exception;

public class MovieNotCreatedException extends Exception {
    public MovieNotCreatedException() {
        super();
    }

    public MovieNotCreatedException(String message) {
        super(message);
    }

    public MovieNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotCreatedException(Throwable cause) {
        super(cause);
    }

    protected MovieNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
