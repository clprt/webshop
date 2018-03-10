package com.company.webshop.dto;

import static com.company.webshop.dto.AccountDto.AccountDtoBuilder.accountDto;

public class AccountDtoTestBuilder {

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String emailAddress = "emailAddress";
    private String password = "password";
    private String address = "address";
    private String phoneNumber = "phoneNumber";

    private AccountDtoTestBuilder() {
    }

    public static AccountDtoTestBuilder anAccountDto() {
        return new AccountDtoTestBuilder();
    }

    public AccountDto build() {
        return accountDto()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmailAddress(emailAddress)
                .withPassword(password)
                .withAddress(address)
                .withPhoneNumber(phoneNumber)
                .build();
    }

    public AccountDtoTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AccountDtoTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountDtoTestBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public AccountDtoTestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public AccountDtoTestBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public AccountDtoTestBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
