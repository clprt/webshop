package com.company.webshop.service;

import com.company.webshop.common.aspects.exception.ResourceNotFoundException;
import com.company.webshop.domain.Account;
import com.company.webshop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplementation implements AccountService {

    private static final String RESOURCE_NOT_FOUND = "Resource not found";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAllAccounts() {
        accountRepository.deleteAllInBatch();
    }
}
