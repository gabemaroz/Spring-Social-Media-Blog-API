package com.example.service;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.Optional;
import java.util.List;

import com.example.repository.*;
import com.example.entity.*;
import com.example.exception.*;

/**
 * Service class for managing message-related operations in the application.
 * 
 * This service provides methods for creating, retrieving, updating, and deleting messages.
 * It utilizes the {@link MessageRepository} for database interactions and ensures that
 * message-related business logic is enforced, including validation and authorization checks.
 * 
 * @see MessageRepository
 * @see AccountRepository
 */
@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    /**
     * Creates a new message if it is valid and the user is authorized.
     * 
     * @param message The message to be created.
     * @return The created {@link Message}.
     * @throws MessageException if the message is corrupt, incomplete, or the user is unauthorized.
     */
    public Message createMessage(Message message) throws MessageException {
        Optional<Account> optionalAccount = accountRepository.findByAccountId(message.getPostedBy());
        if (optionalAccount.isPresent() && !message.getMessageText().isBlank()
                && message.getMessageText().length() < 255) {
            return messageRepository.save(message);
        } else {
            throw new MessageException("Message corrupt, incomplete, or unauthorized.");
        }
    }

    /**
     * Retrieves all messages from the repository.
     * 
     * @return A list of all {@link Message} objects.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Retrieves a message by its unique ID.
     * 
     * @param messageId The ID of the message to be retrieved.
     * @return The {@link Message} if found, or null if not.
     */
    public Message getMessageByMessageId(Integer messageId) {
        Optional<Message> optionalMessage = messageRepository.findByMessageId(messageId);
        return optionalMessage.isPresent() ? optionalMessage.get() : null;
    }

    /**
     * Removes a message by its unique ID.
     * 
     * @param messageId The ID of the message to be deleted.
     * @return 1 if the message was deleted successfully, or null if the message does not exist.
     */
    public Integer removeMessageByMessageId(Integer messageId) {
        if (getMessageByMessageId(messageId) == null) {
            return null;
        } else {
            messageRepository.deleteById(messageId);
            return 1;
        }
    }

    /**
     * Updates the text of an existing message by its ID.
     * 
     * @param messageId The ID of the message to be updated.
     * @param newMessage The message object containing the new text.
     * @return 1 if the message text was successfully updated.
     * @throws MessageException if the original message does not exist or if there are validation issues.
     */
    public Integer changeMessageTextByMessageId(Integer messageId, Message newMessage) throws MessageException {
        Message originalMessage = getMessageByMessageId(messageId);
        if (originalMessage == null) {
            throw new MessageException("Original message does not exist.");
        }
        try {
            originalMessage.setMessageText(newMessage.getMessageText());
            createMessage(originalMessage);
            return 1;
        } catch (MessageException e) {
            throw e;
        }
    }

    /**
     * Retrieves all messages posted by a specific account.
     * 
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return A list of {@link Message} objects posted by the specified account.
     */
    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findMessagesByAccountId(accountId);
    }

}
