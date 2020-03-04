package com.example.packdel.exception;

public class PackageNotValidException extends RuntimeException {

    private final static String MESSAGE = "Package is not valid: ";


    public PackageNotValidException(String reason){
        super(MESSAGE + reason);
    }
}
