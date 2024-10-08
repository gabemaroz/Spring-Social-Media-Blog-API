package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    Optional<Message> findByMessageId(Integer messageId);

    @Query ("FROM Message WHERE postedBy = :account")
    List<Message> findMessagesByAccountId(@Param("account") Integer accountId);

}
