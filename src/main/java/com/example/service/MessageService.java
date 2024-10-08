package com.example.service;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.Optional;

import com.example.repository.*;
import com.example.entity.*;
import com.example.exception.*;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    public Message createMessage(Message message) throws MessageException {
        Optional<Account> optionalAccount = accountRepository.findByAccountId(message.getPostedBy());
        if (optionalAccount.isPresent() && message.getMessageText().length() > 0 && message.getMessageText().length() < 255) {
            return messageRepository.save(message);
        } else {
            throw new MessageException("Message corrupt, incomplete, or unauthorized.");
        }
    }

}
