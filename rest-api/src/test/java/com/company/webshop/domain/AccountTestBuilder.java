package com.company.webshop.domain;

import static com.company.webshop.domain.Account.AccountBuilder.account;

public class AccountTestBuilder {

    private Long id;
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String emailAddress = "emailAddress";
    private String password = "password";
    private String address = "address";
    private String phoneNumber = "phoneNumber";

    private AccountTestBuilder() {
    }

    public static AccountTestBuilder anAccount() {
        return new AccountTestBuilder();
    }

    public Account build() {
        return account()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmailAddress(emailAddress)
                .withPassword(password)
                .withAddress(address)
                .withPhoneNumber(phoneNumber)
                .build();
    }

    public AccountTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AccountTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AccountTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountTestBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public AccountTestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public AccountTestBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public AccountTestBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
