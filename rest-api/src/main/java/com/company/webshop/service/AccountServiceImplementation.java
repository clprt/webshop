package com.company.webshop.service;

import com.company.webshop.common.aspects.exception.NotUniqueWebShopException;
import com.company.webshop.common.aspects.exception.ForbiddenWebshopException;
import com.company.webshop.common.aspects.exception.ResourceNotFoundWebshopException;
import com.company.webshop.domain.Account;
import com.company.webshop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

import static com.company.webshop.common.aspects.exception.ExceptionMessage.EMAIL_ADDRESS_ALREADY_IN_USE;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.FORBIDDEN;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.RESOURCE_NOT_FOUND;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundWebshopException(RESOURCE_NOT_FOUND));
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAllAccounts() {
        accountRepository.deleteAllInBatch();
    }

    @Override
    public void checkAuthorization(Principal principal, Long id) {
        if (!principal.getName().equals("admin")) {
            accountRepository.findByEmailAddress(principal.getName())
                    .filter(account -> account.getId().equals(id))
                    .orElseThrow(() -> new ForbiddenWebshopException(FORBIDDEN));
        }
    }

    @Override
    public void validateEmailAddressIsUnique(Account account) {
        accountRepository.findByEmailAddress(account.getEmailAddress())
                .ifPresent(user -> {
                    throw new NotUniqueWebShopException(EMAIL_ADDRESS_ALREADY_IN_USE);
                });
    }
}
