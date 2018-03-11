package com.company.webshop.controller;

import com.company.webshop.application.WebshopApplication;
import com.company.webshop.common.test.ControllerTest;
import com.company.webshop.domain.Account;
import com.company.webshop.dto.AccountDto;
import com.company.webshop.service.AccountServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import static com.company.webshop.domain.AccountTestBuilder.anAccount;
import static com.company.webshop.dto.AccountDtoTestBuilder.anAccountDto;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = WebshopApplication.class)
public class AccountControllerTest extends ControllerTest {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String PASSWORD = "password";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final AccountDto ACCOUNT_DTO = anAccountDto()
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(PASSWORD)
            .withAddress(ADDRESS)
            .withPhoneNumber(PHONE_NUMBER)
            .build();
    private static final Account ACCOUNT = anAccount()
            .withId(1L)
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(PASSWORD)
            .withAddress(ADDRESS)
            .withPhoneNumber(PHONE_NUMBER)
            .build();
    private static final String RESOURCE_NOT_FOUND = "Resource not found";

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountServiceImplementation accountService;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        accountService.deleteAllAccounts();
    }

    @Test
    public void createAccount() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(ACCOUNT_DTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/api/customers/")));
    }

    @Test
    public void retrieveAccount() throws Exception {
        Account account = accountService.createAccount(ACCOUNT);
        mockMvc.perform(get("/api/customers/" + account.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.customerId", is(account.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.emailAddress", is(EMAIL_ADDRESS)))
                .andExpect(jsonPath("$.address", is(ADDRESS)))
                .andExpect(jsonPath("$.phoneNumber", is(PHONE_NUMBER)));
    }

    @Test
    public void retrieveAccount_NoAccountFoundReturnsResourceNotFoundExceptionResponse() throws Exception {
        mockMvc.perform(get("/api/customers/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error[0]", is(RESOURCE_NOT_FOUND)));
    }
}