package com.multiplex.ticketBooking.exception;

public class SlotNotFoundException extends Exception{
    public SlotNotFoundException() {
        super();
    }

    public SlotNotFoundException(String message) {
        super(message);
    }

    public SlotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SlotNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SlotNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
