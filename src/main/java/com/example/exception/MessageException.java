package com.example.exception;

/**
 * Exception thrown when there is an error related to a message.
 * 
 * This exception is designed to capture issues related to messages, such as formatting,
 * corruption, incomplete information, and other message-related errors in the application.
 * 
 * It extends {@link java.lang.Exception}, enabling it to be caught and handled 
 * as a generic exception with a specific meaning for messaging errors.
 * 
 * @see java.lang.Exception
 */
public class MessageException extends Exception {

    /**
     * Constructs a new MessageException with the specified error message.
     * 
     * @param error The detail message explaining the cause of the exception.
     */
    public MessageException(String error) {
        super(error);
    }
    
}
