package com.company.webshop.service;

import com.company.webshop.domain.Account;

import java.security.Principal;

public interface AccountService {

    Account getAccountById(Long id);

    Account createAccount(Account account);

    void deleteAllAccounts();

    void checkAuthorization(Principal principal, Long id);

    void validateEmailAddressIsUnique(Account account);
}
