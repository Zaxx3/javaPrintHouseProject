package org.example.exceptions;

public class InvalidPaperTypeException extends RuntimeException {
    public InvalidPaperTypeException(String message) {
        super(message);
    }
}
