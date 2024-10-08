package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

import com.example.entity.Message;

/**
 * Repository interface for {@link Message} entity, providing methods to perform
 * CRUD operations and custom queries on message data.
 * 
 * This interface extends {@link org.springframework.data.jpa.repository.JpaRepository}, 
 * inheriting common persistence methods such as {@code save()}, {@code findById()}, and {@code delete()}.
 * It also includes custom queries to retrieve messages by message ID and by account ID of the poster.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.example.entity.Message
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Retrieves a {@link Message} by its unique message ID.
     * 
     * @param messageId The unique ID of the message.
     * @return An {@link Optional} containing the message if found, or empty if not.
     */
    Optional<Message> findByMessageId(Integer messageId);

    /**
     * Retrieves a list of {@link Message} objects posted by the account with the specified ID.
     * 
     * <p>This query uses a custom JPQL statement to filter messages based on the account ID of the user who posted them.
     * 
     * @param accountId The unique ID of the account that posted the messages.
     * @return A list of {@link Message} objects posted by the specified account.
     */
    @Query("FROM Message WHERE postedBy = :account")
    List<Message> findMessagesByAccountId(@Param("account") Integer accountId);

}