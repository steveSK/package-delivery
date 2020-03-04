package com.example.packdel.exception;

public class InputException extends RuntimeException {

    private final static String MESSAGE = "Exception occurs during input processing: ";

    public InputException(String reason, Throwable cause) {
        super(MESSAGE + reason, cause);
    }
}
