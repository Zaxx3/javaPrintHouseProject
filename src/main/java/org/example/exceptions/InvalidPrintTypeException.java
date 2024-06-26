package org.example.exceptions;

public class InvalidPrintTypeException extends RuntimeException {
    public InvalidPrintTypeException(String message) {
        super(message);
    }
}
