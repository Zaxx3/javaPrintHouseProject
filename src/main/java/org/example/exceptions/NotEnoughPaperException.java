package org.example.exceptions;

public class NotEnoughPaperException extends RuntimeException {
    public NotEnoughPaperException(String message) {
        super(message);
    }
}
