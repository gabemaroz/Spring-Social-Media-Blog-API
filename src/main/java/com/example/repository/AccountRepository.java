package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.entity.Account;

/**
 * Repository interface for {@link Account} entity, providing methods to perform
 * CRUD operations and custom queries on the account data.
 * 
 * This interface extends {@link org.springframework.data.jpa.repository.JpaRepository}, 
 * inheriting common persistence methods like {@code save()}, {@code findById()}, and {@code delete()}.
 * It also includes custom queries to find accounts by username, username and password, 
 * and account ID.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.example.entity.Account
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Retrieves an {@link Account} by its username.
     * 
     * @param username The username of the account.
     * @return An {@link Optional} containing the account if found, or empty if not.
     */
    Optional<Account> findByUsername(String username);

    /**
     * Retrieves an {@link Account} by its username and password.
     * 
     * @param username The username of the account.
     * @param password The password of the account.
     * @return An {@link Optional} containing the account if the username and password match, or empty if not.
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);

    /**
     * Retrieves an {@link Account} by its unique account ID.
     * 
     * @param accountId The unique ID of the account.
     * @return An {@link Optional} containing the account if found, or empty if not.
     */
    Optional<Account> findByAccountId(Integer accountId);
}
