package com.company.webshop.dto;

import com.company.webshop.common.aspects.ddd.ValueObject;

public class AccountDto extends ValueObject {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String address;
    private String phoneNumber;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public static class AccountDtoBuilder {

        private AccountDto accountDto;

        private AccountDtoBuilder() {
            this.accountDto = new AccountDto();
        }

        public static AccountDtoBuilder accountDto() {
            return new AccountDtoBuilder();
        }

        public AccountDto build() {
            return this.accountDto;
        }

        public AccountDtoBuilder withFirstName(String firstName) {
            this.accountDto.setFirstName(firstName);
            return this;
        }

        public AccountDtoBuilder withLastName(String lastName) {
            this.accountDto.setLastName(lastName);
            return this;
        }

        public AccountDtoBuilder withEmailAddress(String emailAddress) {
            this.accountDto.setEmailAddress(emailAddress);
            return this;
        }

        public AccountDtoBuilder withPassword(String password) {
            this.accountDto.setPassword(password);
            return this;
        }

        public AccountDtoBuilder withAddress(String address) {
            this.accountDto.setAddress(address);
            return this;
        }

        public AccountDtoBuilder withPhoneNumber(String phoneNumber) {
            this.accountDto.setPhoneNumber(phoneNumber);
            return this;
        }
    }
}
