package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.service.*;
import com.example.entity.*;
import com.example.exception.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

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

    @PostMapping("login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        try {
            Account authorizedAccount = accountService.loginAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(authorizedAccount);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    } 

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            Message validMessage = messageService.createMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(validMessage);
        } catch (MessageException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> retrieveAllMessages() {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> retrieveMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageByMessageId(messageId));
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.removeMessageByMessageId(messageId));
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> replaceMessageTextByMessageId(@PathVariable Integer messageId, @RequestBody Message message) {
        try {
            Integer rowsChanged = messageService.changeMessageTextByMessageId(messageId, message);
            return ResponseEntity.status(HttpStatus.OK).body(rowsChanged);
        } catch (MessageException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("accounts/{accountId}")
    public ResponseEntity<List<Message>> retrieveAllMessagesByAccount(@PathVariable Integer accountId) {
        List <Message> messages = null;
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }






}
