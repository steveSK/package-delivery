package com.example.packdel.exception;

public class FileNotFoundException extends RuntimeException {

    public static final String MESSAGE = "File <FILE> was not found:";

    public FileNotFoundException(String file) {
        super(MESSAGE.replace("<FILE>", file));
    }

}
