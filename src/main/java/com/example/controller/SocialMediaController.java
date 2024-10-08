package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.service.*;
import com.example.entity.*;

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
    public ResponseEntity registerAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping("login")
    public ResponseEntity loginAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(account);
    } 

    @PostMapping("messages")
    public ResponseEntity createMessage(@RequestBody Message message) {
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("messages")
    public ResponseEntity retrieveAllMessages() {
        List <Message> messages = null;
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity retrieveMessageById(@PathVariable String messageId) {
        Message message = null;
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity deleteMessageById(@PathVariable String messageId) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity replaceMessageById(@PathVariable String messageId) {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("accounts/{accountId}")
    public ResponseEntity retrieveAllMessagesByAccount(@PathVariable String accountId) {
        List <Message> messages = null;
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }






}
