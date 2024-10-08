package com.example.exception;

public class UnauthorizedException extends Exception {
    
    public UnauthorizedException(String error) {
        super(error);
    }
    
}
