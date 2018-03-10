package com.company.webshop.dto;

import com.company.webshop.common.aspects.ddd.ValueObject;

public class CustomerDto extends ValueObject {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String phoneNumber;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class CustomerDtoBuilder {

        private CustomerDto customerDto;

        private CustomerDtoBuilder() {
            this.customerDto = new CustomerDto();
        }

        public static CustomerDtoBuilder customerDto() {
            return new CustomerDtoBuilder();
        }

        public CustomerDto build() {
            return this.customerDto;
        }

        public CustomerDtoBuilder withCustomerId(Long customerId) {
            this.customerDto.setCustomerId(customerId);
            return this;
        }

        public CustomerDtoBuilder withFirstName(String firstName) {
            this.customerDto.setFirstName(firstName);
            return this;
        }

        public CustomerDtoBuilder withLastName(String lastName) {
            this.customerDto.setLastName(lastName);
            return this;
        }

        public CustomerDtoBuilder withEmailAddress(String emailAddress) {
            this.customerDto.setEmailAddress(emailAddress);
            return this;
        }

        public CustomerDtoBuilder withAddress(String address) {
            this.customerDto.setAddress(address);
            return this;
        }

        public CustomerDtoBuilder withPhoneNumber(String phoneNumber) {
            this.customerDto.setPhoneNumber(phoneNumber);
            return this;
        }
    }
}
