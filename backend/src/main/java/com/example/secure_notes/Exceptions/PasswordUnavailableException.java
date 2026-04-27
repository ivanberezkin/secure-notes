package com.example.secure_notes.Exceptions;

public class PasswordUnavailableException extends RuntimeException {
    public PasswordUnavailableException(String message) {
        super(message);
    }
}
