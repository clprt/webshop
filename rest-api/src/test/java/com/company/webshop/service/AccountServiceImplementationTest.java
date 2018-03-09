package com.company.webshop.service;

import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Account;
import com.company.webshop.repository.AccountRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.company.webshop.domain.AccountTestBuilder.anAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceImplementationTest extends UnitTest {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String PASSWORD = "password";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final Account ACCOUNT = anAccount()
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(PASSWORD)
            .withAddress(ADDRESS)
            .withPhoneNumber(PHONE_NUMBER)
            .build();

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImplementation accountService;

    @Test
    public void getAccountById_ReturnsAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(ACCOUNT));

        Account result = accountService.getAccountById(1L);

        assertThat(result).isEqualTo(ACCOUNT);
    }

    @Test
    public void createAccount_ReturnsCreatedAccount() {
        Account account = anAccount()
                .withId(1L)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmailAddress(EMAIL_ADDRESS)
                .withPassword(PASSWORD)
                .withAddress(ADDRESS)
                .withPhoneNumber(PHONE_NUMBER)
                .build();
        when(accountRepository.save(ACCOUNT)).thenReturn(account);

        Account result = accountService.createAccount(ACCOUNT);

        assertThat(result).isEqualTo(account);
    }

    @Test
    public void deleteAllAccounts_CallsDeleteAllInBatchOnTheAccountRepository() {
        accountService.deleteAllAccounts();

        verify(accountRepository).deleteAllInBatch();
    }
}