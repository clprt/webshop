package com.company.webshop.mapper;

import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Account;
import com.company.webshop.dto.AccountDto;
import com.company.webshop.dto.CustomerDto;
import org.junit.Test;
import org.mockito.InjectMocks;

import static com.company.webshop.domain.AccountTestBuilder.anAccount;
import static com.company.webshop.dto.AccountDtoTestBuilder.anAccountDto;
import static com.company.webshop.dto.CustomerDtoTestBuilder.aCustomerDto;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountMapperTest extends UnitTest {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String PASSWORD = "password";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final Long CUSTOMER_ID = 1L;

    private static final AccountDto ACCOUNT_DTO = anAccountDto()
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(PASSWORD)
            .withAddress(ADDRESS)
            .withPhoneNumber(PHONE_NUMBER)
            .build();
    private static final Account ACCOUNT = anAccount()
            .withId(CUSTOMER_ID)
            .withFirstName(FIRST_NAME)
            .withLastName(LAST_NAME)
            .withEmailAddress(EMAIL_ADDRESS)
            .withPassword(PASSWORD)
            .withAddress(ADDRESS)
            .withPhoneNumber(PHONE_NUMBER)
            .build();

    @InjectMocks
    private AccountMapper accountMapper;

    @Test
    public void toAccount_MapsAccountDtoToAccount() {
        Account result = accountMapper.toAccount(ACCOUNT_DTO);

        Account expected = anAccount()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmailAddress(EMAIL_ADDRESS)
                .withPassword(PASSWORD)
                .withAddress(ADDRESS)
                .withPhoneNumber(PHONE_NUMBER)
                .build();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void toCustomerDto_MapsAccountToCustomerDto() {
        CustomerDto result = accountMapper.toCustomerDto(ACCOUNT);

        CustomerDto expected = aCustomerDto()
                .withCustomerId(CUSTOMER_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmailAddress(EMAIL_ADDRESS)
                .withAddress(ADDRESS)
                .withPhoneNumber(PHONE_NUMBER)
                .build();
        assertThat(result).isEqualTo(expected);
    }
}