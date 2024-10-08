package com.example.service;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.Optional;
import java.util.List;

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
        if (optionalAccount.isPresent() && !message.getMessageText().isBlank()
                && message.getMessageText().length() < 255) {
            return messageRepository.save(message);
        } else {
            throw new MessageException("Message corrupt, incomplete, or unauthorized.");
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(Integer messageId) {
        Optional<Message> optionalMessage = messageRepository.findByMessageId(messageId);
        return optionalMessage.isPresent() ? optionalMessage.get() : null;
    }

    public Integer removeMessageByMessageId(Integer messageId) {
        if (getMessageByMessageId(messageId) == null) {
            return null;
        } else {
            messageRepository.deleteById(messageId);
            return 1;
        }
    }

    public Integer changeMessageTextByMessageId(Integer messageId, Message newMessage) throws MessageException {
        Message originalMessage = getMessageByMessageId(messageId);
        if (originalMessage == null) {
            throw new MessageException("Original message does not exist.");
        }
        try {
            originalMessage.setMessageText(newMessage.getMessageText());
            createMessage(originalMessage);
            return 1;
        } catch (MessageException e){
            throw e;
        }
    }

}
