package com.company.webshop.dto;

import static com.company.webshop.dto.CustomerDto.CustomerDtoBuilder.customerDto;

public class CustomerDtoTestBuilder {

    private Long customerId = 1L;
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String emailAddress = "emailAddress";
    private String address = "address";
    private String phoneNumber = "phoneNumber";

    private CustomerDtoTestBuilder() {
    }

    public static CustomerDtoTestBuilder aCustomerDto() {
        return new CustomerDtoTestBuilder();
    }

    public CustomerDto build() {
        return customerDto()
                .withCustomerId(customerId)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmailAddress(emailAddress)
                .withAddress(address)
                .withPhoneNumber(phoneNumber)
                .build();
    }

    public CustomerDtoTestBuilder withCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public CustomerDtoTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerDtoTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerDtoTestBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public CustomerDtoTestBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerDtoTestBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
