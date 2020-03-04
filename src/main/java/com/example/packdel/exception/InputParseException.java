package com.example.packdel.exception;

public class InputParseException extends RuntimeException {

    public final static String MESSAGE = "There were an error parsing input '<INPUT>' : ";

    public InputParseException(String input, String reason){
        super(MESSAGE.replace("<INPUT>",input) + reason);
    }
}
