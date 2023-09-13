package com.example.ideas.exception;

public class NoAuthorizationException extends Exception{
    public NoAuthorizationException(String message) {
        super(message);
    }
}
