package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByMessageId(Integer messageId);

}
