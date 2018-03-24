package com.company.webshop.controller;

import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Account;
import com.company.webshop.dto.AccountDto;
import com.company.webshop.mapper.AccountMapper;
import com.company.webshop.service.AccountService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.company.webshop.domain.AccountTestBuilder.anAccount;
import static com.company.webshop.dto.AccountDtoTestBuilder.anAccountDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AccountControllerUnitTest extends UnitTest {

    private static final AccountDto ACCOUNT_DTO = anAccountDto().build();
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final Account ACCOUNT = anAccount().withEmailAddress(EMAIL_ADDRESS).build();
    private static final long ID = 1L;
    private static final Account PERSISTED_ACCOUNT = anAccount().withId(ID).build();

    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void createAccount_ReturnsResponseEntityWithCorrectUriLocation() {
        when(accountMapper.toAccount(ACCOUNT_DTO)).thenReturn(ACCOUNT);
        when(accountService.createAccount(ACCOUNT)).thenReturn(PERSISTED_ACCOUNT);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(8080);
        request.setRequestURI("/api/accounts");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<?> responseEntity = accountController.createAccount(ACCOUNT_DTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation().toString()).isEqualTo(request.getRequestURL() + "/" + ID);
    }
}