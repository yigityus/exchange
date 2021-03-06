package com.example.exchange.exception;

import java.time.Instant;

public class RequestParamMissingException extends RuntimeException {
    private static final String message = "At least on of a date or a trx id should be provided";

    public RequestParamMissingException(Instant now) {
        super(message + " " + now);
    }
}
