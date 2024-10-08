package com.example.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.*;

/**
 * Service class for managing account-related operations, including account creation and login.
 * 
 * This class contains business logic for handling accounts, utilizing {@link AccountRepository}
 * to perform database operations. It provides methods to create new accounts and authenticate existing ones.
 * 
 * Account creation involves validating the username and password, checking for duplicates,
 * and saving the account to the repository. Login operations validate the credentials against stored accounts.
 * 
 * @see AccountRepository
 * @see Account
 */
@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    /**
     * Creates a new account with the provided details.
     * 
     * This method validates the username and password length, checks for duplicate usernames, 
     * and saves the account if valid. 
     * 
     * @param account The account details to be created.
     * @return The created {@link Account} object.
     * @throws DuplicateUsernameException If the username already exists in the system.
     * @throws Exception If there are issues with the username or password (e.g., invalid length).
     */
    public Account createAccount(Account account) throws DuplicateUsernameException, Exception {
        if (account.getUsername().length() < 1 || account.getPassword().length() < 4) {
            throw new Exception("Username or password issue.");
        }
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        if (optionalAccount.isPresent()) {
            throw new DuplicateUsernameException("Username already exists.");
        }
        return accountRepository.save(account);
    }

    /**
     * Authenticates an account using the provided credentials.
     * 
     * This method checks the username and password against existing accounts. If the credentials 
     * are invalid, an exception is thrown.
     * 
     * @param account The account details for authentication, including username and password.
     * @return The authenticated {@link Account} object if login is successful.
     * @throws UnauthorizedException If the provided credentials do not match any existing account.
     */
    public Account loginAccount(Account account) throws UnauthorizedException {
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (optionalAccount.isEmpty()) {
            throw new UnauthorizedException("Account is not authorized.");
        }
        return optionalAccount.get();
    }


}
