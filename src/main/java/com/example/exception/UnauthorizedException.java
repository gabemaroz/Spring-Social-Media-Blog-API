package com.example.exception;

/**
 * Exception thrown when an unauthorized action is attempted.
 * 
 * This exception should be used to indicate that the current user or process 
 * does not have the necessary permissions to perform the attempted action.
 * 
 * It extends {@link java.lang.Exception}, allowing it to be caught as a general exception, 
 * but providing a more specific meaning for handling unauthorized access scenarios.
 * 
 * @see java.lang.Exception
 */
public class UnauthorizedException extends Exception {

    /**
     * Constructs a new UnauthorizedException with the specified error message.
     * 
     * @param error The detail message explaining the reason for the exception.
     */
    public UnauthorizedException(String error) {
        super(error);
    }  
}
