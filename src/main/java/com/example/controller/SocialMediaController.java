package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.service.*;
import com.example.entity.*;
import com.example.exception.*;

/**
 * REST controller for handling social media-related operations, including
 * account registration, login, message posting, retrieval, updating, and deletion.
 * 
 * This controller provides endpoints for managing user accounts and messages,
 * utilizing {@link AccountService} for account-related operations and
 * {@link MessageService} for message-related operations.
 * 
 * The controller handles various HTTP requests such as POST, GET, DELETE, and PATCH,
 * responding with appropriate HTTP status codes and payloads based on the operation.
 * 
 * @see AccountService
 * @see MessageService
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    /**
     * Constructs the controller with injected dependencies for account and message services.
     * 
     * @param accountService Service for managing account operations.
     * @param messageService Service for managing message operations.
     */
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Registers a new account.
     * 
     * @param account The account to be registered.
     * @return A {@link ResponseEntity} containing the created account and HTTP status.
     *         Returns HTTP 409 (Conflict) if the username is already taken, or HTTP 400 (Bad Request) for other errors.
     * @see AccountService#createAccount(Account)
     */
    @PostMapping("register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        try {
            Account createdAccount = accountService.createAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(createdAccount);
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Logs in an existing account.
     * 
     * @param account The account to be logged in.
     * @return A {@link ResponseEntity} containing the authenticated account and HTTP status.
     *         Returns HTTP 401 (Unauthorized) if login fails due to invalid credentials.
     * @see AccountService#loginAccount(Account)
     */
    @PostMapping("login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        try {
            Account authorizedAccount = accountService.loginAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(authorizedAccount);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    } 

    /**
     * Creates a new message.
     * 
     * @param message The message to be created.
     * @return A {@link ResponseEntity} containing the created message and HTTP status.
     *         Returns HTTP 400 (Bad Request) if the message is invalid.
     * @see MessageService#createMessage(Message)
     */
    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            Message validMessage = messageService.createMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(validMessage);
        } catch (MessageException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Retrieves all messages.
     * 
     * @return A {@link ResponseEntity} containing the list of all messages and HTTP status.
     * @see MessageService#getAllMessages()
     */
    @GetMapping("messages")
    public ResponseEntity<List<Message>> retrieveAllMessages() {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }

    /**
     * Retrieves a message by its ID.
     * 
     * @param messageId The ID of the message to be retrieved.
     * @return A {@link ResponseEntity} containing the message and HTTP status.
     * @see MessageService#getMessageByMessageId(Integer)
     */
    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> retrieveMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageByMessageId(messageId));
    }

    /**
     * Deletes a message by its ID.
     * 
     * @param messageId The ID of the message to be deleted.
     * @return A {@link ResponseEntity} containing the ID of the deleted message and HTTP status.
     * @see MessageService#removeMessageByMessageId(Integer)
     */
    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.removeMessageByMessageId(messageId));
    }

    /**
     * Updates the text of a message by its ID.
     * 
     * @param messageId The ID of the message to be updated.
     * @param message   The updated message object containing new text.
     * @return A {@link ResponseEntity} containing the ID of the updated message and HTTP status.
     *         Returns HTTP 400 (Bad Request) if the update is invalid.
     * @see MessageService#changeMessageTextByMessageId(Integer, Message)
     */
    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> replaceMessageTextByMessageId(@PathVariable Integer messageId, @RequestBody Message message) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(messageService.changeMessageTextByMessageId(messageId, message));
        } catch (MessageException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Retrieves all messages posted by a specific account.
     * 
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return A {@link ResponseEntity} containing the list of messages posted by the account and HTTP status.
     * @see MessageService#getMessagesByAccountId(Integer)
     */
    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> retrieveAllMessagesByAccount(@PathVariable Integer accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessagesByAccountId(accountId));
    }

}
