package com.company.webshop.service;

import com.company.webshop.common.aspects.exception.NotUniqueWebShopException;
import com.company.webshop.common.aspects.exception.ForbiddenWebshopException;
import com.company.webshop.common.aspects.exception.ResourceNotFoundWebshopException;
import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Account;
import com.company.webshop.repository.AccountRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.security.Principal;
import java.util.Optional;

import static com.company.webshop.common.aspects.exception.ExceptionMessage.EMAIL_ADDRESS_ALREADY_IN_USE;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.FORBIDDEN;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.RESOURCE_NOT_FOUND;
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
    private static final Long ID = 1L;
    private static final Long OTHER_ID = 2L;
    private static final Account ACCOUNT_WITH_ID = anAccount()
            .withId(ID)
            .withEmailAddress(EMAIL_ADDRESS)
            .build();
    private static final Account ACCOUNT_WITH_OTHER_ID = anAccount()
            .withId(OTHER_ID)
            .withEmailAddress(EMAIL_ADDRESS)
            .build();
    private static final String ALREADY_EXISTING_EMAIL_ADDRESS = "alreadyExistingEmailAddress";
    private static final Account ACCOUNT_WITH_ALREADY_EXISTING_EMAIL_ADDRESS = anAccount()
            .withEmailAddress(ALREADY_EXISTING_EMAIL_ADDRESS)
            .build();
    private static final String NOT_YET_EXISTING_EMAIL_ADDRESS = "notYetExistingEmailAddress";
    private static final Account ACCOUNT_WITH_NOT_YET_EXISTING_EMAIL_ADDRESS = anAccount()
            .withEmailAddress(NOT_YET_EXISTING_EMAIL_ADDRESS)
            .build();

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private Principal principal;

    @InjectMocks
    private AccountServiceImplementation accountService;

    @Test
    public void getAccountById_WhenAccountExistsReturnsAccount() {
        when(accountRepository.findById(ID)).thenReturn(Optional.of(ACCOUNT));

        Account result = accountService.getAccountById(ID);

        assertThat(result).isEqualTo(ACCOUNT);
    }

    @Test
    public void getAccountById_WhenAccountDoesNotExistThrowsResourceNotFoundWebshopException() throws Exception {
        expectedException.expect(ResourceNotFoundWebshopException.class);
        expectedException.expectMessage(RESOURCE_NOT_FOUND.getValue());
        when(accountRepository.findById(ID)).thenReturn(Optional.empty());

        accountService.getAccountById(ID);
    }

    @Test
    public void createAccount_ReturnsCreatedAccount() {
        Account account = anAccount()
                .withId(ID)
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

    @Test
    public void checkAuthorization_AllowsAccountAdmin() {
        when(principal.getName()).thenReturn("admin");

        accountService.checkAuthorization(principal, ID);
    }

    @Test
    public void checkAuthorization_AllowsAccountWithId() {
        when(principal.getName()).thenReturn(EMAIL_ADDRESS);
        when(accountRepository.findByEmailAddress(EMAIL_ADDRESS)).thenReturn(Optional.of(ACCOUNT_WITH_ID));

        accountService.checkAuthorization(principal, ID);
    }

    @Test
    public void checkAuthorization_ThrowsForbiddenWebshopExceptionForAccountWithOtherId() throws Exception {
        expectedException.expect(ForbiddenWebshopException.class);
        expectedException.expectMessage(FORBIDDEN.getValue());
        when(principal.getName()).thenReturn(EMAIL_ADDRESS);
        when(accountRepository.findByEmailAddress(EMAIL_ADDRESS)).thenReturn(Optional.of(ACCOUNT_WITH_OTHER_ID));

        accountService.checkAuthorization(principal, ID);
    }

    @Test
    public void validateEmailAddressIsUnique_AllowsNotYetExistingEmailAddress() {
        when(accountRepository.findByEmailAddress(NOT_YET_EXISTING_EMAIL_ADDRESS)).thenReturn(Optional.empty());

        accountService.validateEmailAddressIsUnique(ACCOUNT_WITH_NOT_YET_EXISTING_EMAIL_ADDRESS);
    }

    @Test
    public void validateEmailAddressIsUnique_ThrowsNotUniqueWebshopExceptionWithMessageEmailAddressAlreadyInUse() throws Exception {
        expectedException.expect(NotUniqueWebShopException.class);
        expectedException.expectMessage(EMAIL_ADDRESS_ALREADY_IN_USE.getValue());
        when(accountRepository.findByEmailAddress(ALREADY_EXISTING_EMAIL_ADDRESS)).thenReturn(Optional.of(ACCOUNT));

        accountService.validateEmailAddressIsUnique(ACCOUNT_WITH_ALREADY_EXISTING_EMAIL_ADDRESS);
    }
}