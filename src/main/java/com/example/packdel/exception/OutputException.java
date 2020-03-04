package com.example.packdel.exception;

public class OutputException extends RuntimeException {

    private final static String MESSAGE = "Exception occurs during output processing: ";

    public OutputException(String reason, Throwable cause) {
        super(MESSAGE + reason, cause);
    }

}
