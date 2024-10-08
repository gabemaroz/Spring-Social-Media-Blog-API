package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.*;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

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

    public Account loginAccount(Account account) throws UnauthorizedException {
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (optionalAccount.isEmpty()) {
            throw new UnauthorizedException("Account is not authorized.");
        }
        return optionalAccount.get();
    }


}
