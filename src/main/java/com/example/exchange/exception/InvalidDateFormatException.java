package com.example.exchange.exception;

import java.time.Instant;

public class InvalidDateFormatException extends RuntimeException {
    private static final String message = "Date should be formatted like yyyy-MM-dd";

    public InvalidDateFormatException(Instant now) {
        super(message + " " + now);
    }
}
