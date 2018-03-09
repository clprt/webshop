package com.company.webshop.service;

import com.company.webshop.domain.Account;

public interface AccountService {

    Account getAccountById(Long id);

    Account createAccount(Account account);

    void deleteAllAccounts();

}
