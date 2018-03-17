package com.company.webshop.dto;

import com.company.webshop.common.aspects.ddd.ValueObject;
import com.company.webshop.common.aspects.validation.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.company.webshop.common.aspects.validation.ValidationMessage.ADDRESS_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.EMAIL_ADDRESS_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.EMAIL_ADDRESS_HAS_AN_INCORRECT_FORMAT;
import static com.company.webshop.common.aspects.validation.ValidationMessage.FIRSTNAME_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.LASTNAME_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.common.aspects.validation.ValidationMessage.PASSWORD_CANNOT_BE_NULL;
import static com.company.webshop.common.aspects.validation.ValidationMessage.PASSWORD_MUST_CONTAIN_AT_LEAST_8_CHARACTERS;

public class AccountDto extends ValueObject {

    @Length
    @NotBlank(message = FIRSTNAME_CANNOT_BE_NULL_OR_BLANK)
    private String firstName;
    @Length
    @NotBlank(message = LASTNAME_CANNOT_BE_NULL_OR_BLANK)
    private String lastName;
    @NotBlank(message = EMAIL_ADDRESS_CANNOT_BE_NULL_OR_BLANK)
    @Email(message = EMAIL_ADDRESS_HAS_AN_INCORRECT_FORMAT)
    private String emailAddress;
    @Length
    @NotNull(message = PASSWORD_CANNOT_BE_NULL)
    @Size(min = 8, message = PASSWORD_MUST_CONTAIN_AT_LEAST_8_CHARACTERS)
    private String password;
    @Length
    @NotBlank(message = ADDRESS_CANNOT_BE_NULL_OR_BLANK)
    private String address;
    @Length(value = 25)
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
