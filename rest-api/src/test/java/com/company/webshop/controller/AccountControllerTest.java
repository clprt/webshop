package com.company.webshop.controller;

import com.company.webshop.application.WebshopApplication;
import com.company.webshop.common.test.ControllerTest;
import com.company.webshop.domain.Account;
import com.company.webshop.dto.AccountDto;
import com.company.webshop.service.AccountServiceImplementation;
import com.sun.security.auth.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import static com.company.webshop.common.aspects.exception.ExceptionMessage.EMAIL_ADDRESS_ALREADY_IN_USE;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.FORBIDDEN;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.RESOURCE_NOT_FOUND;
import static com.company.webshop.common.aspects.validation.ValidationMessage.ADDRESS_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.EMAIL_ADDRESS_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.EMAIL_ADDRESS_HAS_AN_INCORRECT_FORMAT;
import static com.company.webshop.common.aspects.validation.ValidationMessage.FIELD_LENGTH_EXCEEDED;
import static com.company.webshop.common.aspects.validation.ValidationMessage.FIRSTNAME_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.LASTNAME_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.PASSWORD_CANNOT_BE_NULL;
import static com.company.webshop.common.aspects.validation.ValidationMessage.PASSWORD_MUST_CONTAIN_AT_LEAST_8_CHARACTERS;
import static com.company.webshop.domain.AccountTestBuilder.anAccount;
import static com.company.webshop.dto.AccountDtoTestBuilder.anAccountDto;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
    private static final String EMAIL_ADDRESS = "name@domain";
    private static final String PASSWORD = "password";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String STRING_256 = StringUtils.repeat("x", 256);
    private static final String EMAIL_ADDRESS_129 = StringUtils.repeat("x", 64).concat("@").concat(StringUtils.repeat("x", 64));
    private static final String STRING_26 = StringUtils.repeat("x", 26);
    private static final AccountDto ACCOUNT_DTO = anAccountDto()
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(PASSWORD)
            .withAddress(ADDRESS)
            .withPhoneNumber(PHONE_NUMBER)
            .build();
    private static final AccountDto ACCOUNT_DTO_WITH_NULLS_FOR_REQUIRED_FIELDS = anAccountDto()
            .withFirstName(null)
            .withLastName(null)
            .withEmailAddress(null)
            .withPassword(null)
            .withAddress(null)
            .build();
    private static final AccountDto ACCOUNT_DTO_WITH_BLANKS_FOR_REQUIRED_FIELDS = anAccountDto()
            .withFirstName("")
            .withLastName("")
            .withEmailAddress("")
            .withPassword("")
            .withAddress("")
            .build();
    private static final AccountDto ACCOUNT_DTO_WITH_EXCEEDED_FIELD_LENGTHS = anAccountDto()
            .withFirstName(STRING_256)
            .withLastName(STRING_256)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(STRING_256)
            .withAddress(STRING_256)
            .withPhoneNumber(STRING_26)
            .build();
    private static final Account ACCOUNT = anAccount()
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(PASSWORD)
            .withAddress(ADDRESS)
            .withPhoneNumber(PHONE_NUMBER)
            .build();
    private static final String OTHER_EMAIL_ADDRESS = "othername@domain";
    private static final Account OTHER_ACCOUNT = anAccount()
            .withEmailAddress(OTHER_EMAIL_ADDRESS)
            .build();

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
    public void createAccount_ValidAccountGetsCreatedAndReturnsLocation() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(ACCOUNT_DTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/api/customers/")));
    }

    @Test
    public void createAccount_AccountWithNullValuesForRequiredFieldsReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(ACCOUNT_DTO_WITH_NULLS_FOR_REQUIRED_FIELDS)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsInAnyOrder(
                        FIRSTNAME_CANNOT_BE_NULL_OR_BLANK,
                        LASTNAME_CANNOT_BE_NULL_OR_BLANK,
                        EMAIL_ADDRESS_CANNOT_BE_NULL_OR_BLANK,
                        PASSWORD_CANNOT_BE_NULL,
                        ADDRESS_CANNOT_BE_NULL_OR_BLANK
                )));
    }

    @Test
    public void createAccount_AccountWithBlankValuesForRequiredFieldsReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(ACCOUNT_DTO_WITH_BLANKS_FOR_REQUIRED_FIELDS)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsInAnyOrder(
                        FIRSTNAME_CANNOT_BE_NULL_OR_BLANK,
                        LASTNAME_CANNOT_BE_NULL_OR_BLANK,
                        EMAIL_ADDRESS_CANNOT_BE_NULL_OR_BLANK,
                        PASSWORD_MUST_CONTAIN_AT_LEAST_8_CHARACTERS,
                        ADDRESS_CANNOT_BE_NULL_OR_BLANK
                )));
    }

    @Test
    public void createAccount_AccountWithInvalidEmailAddressFormatReturnsBadRequestResponseWithIndicativeErrorMessage() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anAccountDto().withEmailAddress("invalidEmailAddressFormat").build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error[0]", is(EMAIL_ADDRESS_HAS_AN_INCORRECT_FORMAT)));
    }

    @Test
    public void createAccount_AccountWithInvalidPasswordReturnsBadRequestResponseWithIndicativeErrorMessage() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anAccountDto().withPassword("1234567").build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error[0]", is(PASSWORD_MUST_CONTAIN_AT_LEAST_8_CHARACTERS)));
    }

    @Test
    public void createAccount_AccountWithEmailAddressAlreadyInUseReturnsConflictResponseWithIndicativeErrorMessage() throws Exception {
        Account account = accountService.createAccount(ACCOUNT);
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anAccountDto().withEmailAddress(EMAIL_ADDRESS).build())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error[0]", is(EMAIL_ADDRESS_ALREADY_IN_USE.getValue())));
    }

    @Test
    public void retrieveAccount_UserCorrespondingToIdGetsCustomerDetails() throws Exception {
        Account account = accountService.createAccount(ACCOUNT);
        mockMvc.perform(get("/api/customers/" + account.getId())
                .principal(new UserPrincipal(account.getEmailAddress()))
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
    public void retrieveAccount_AdminGetsCustomerDetails() throws Exception {
        Account account = accountService.createAccount(ACCOUNT);
        mockMvc.perform(get("/api/customers/" + account.getId())
                .principal(new UserPrincipal("admin"))
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
    public void retrieveAccount_UserCorrespondingToAnotherIdGetsForbiddenResponse() throws Exception {
        Account account = accountService.createAccount(ACCOUNT);
        Account otherAccount = accountService.createAccount(OTHER_ACCOUNT);
        mockMvc.perform(get("/api/customers/" + account.getId())
                .principal(new UserPrincipal(otherAccount.getEmailAddress()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.error[0]", is(FORBIDDEN.getValue())));
    }

    @Test
    public void retrieveAccount_AdminGetsResourceNotFoundResponseWhenNoAccountCorrespondsToId() throws Exception {
        mockMvc.perform(get("/api/customers/1")
                .principal(new UserPrincipal("admin"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error[0]", is(RESOURCE_NOT_FOUND.getValue())));
    }

    @Test
    public void createAccount_AccountWithExceededFieldLengthsReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(ACCOUNT_DTO_WITH_EXCEEDED_FIELD_LENGTHS)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", contains(
                        FIELD_LENGTH_EXCEEDED,
                        FIELD_LENGTH_EXCEEDED,
                        FIELD_LENGTH_EXCEEDED,
                        FIELD_LENGTH_EXCEEDED,
                        FIELD_LENGTH_EXCEEDED
                )));
    }

    @Test
    public void createAccount_AccountWithTooLongEmailAddressReturnsBadRequestResponseWithIndicativeErrorMessage() throws Exception {
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anAccountDto().withEmailAddress(EMAIL_ADDRESS_129).build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error[0]", is(EMAIL_ADDRESS_HAS_AN_INCORRECT_FORMAT)));
    }

}