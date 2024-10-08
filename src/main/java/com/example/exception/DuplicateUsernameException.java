package com.example.exception;

/**
 * Exception thrown when an attempt is made to register or use a username 
 * that already exists in the system.
 * 
 * This exception is useful for handling user registration processes, ensuring that
 * usernames are unique and preventing duplicates in the system.
 * 
 * It extends {@link java.lang.Exception}, allowing it to be caught as a general exception, 
 * but providing specific meaning for scenarios involving duplicate usernames.
 * 
 * @see java.lang.Exception
 */
public class DuplicateUsernameException extends Exception {

    /**
     * Constructs a new DuplicateUsernameException with the specified error message.
     * 
     * @param error The detail message explaining the reason for the exception.
     */
    public DuplicateUsernameException(String error) {
        super(error);
    }   
}

