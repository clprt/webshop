package com.company.webshop.domain;

import com.company.webshop.common.aspects.ddd.ValueObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends ValueObject {

    public static final String TABLE = "account";
    public static final String ID_COLUMN = TABLE + "_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Account() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static class AccountBuilder {

        private Account account;

        private AccountBuilder() {
            this.account = new Account();
        }

        public static AccountBuilder account() {
            return new AccountBuilder();
        }

        public Account build() {
            return this.account;
        }

        public AccountBuilder withId(Long id) {
            this.account.setId(id);
            return this;
        }

        public AccountBuilder withFirstName(String firstName) {
            this.account.setFirstName(firstName);
            return this;
        }

        public AccountBuilder withLastName(String lastName) {
            this.account.setLastName(lastName);
            return this;
        }

        public AccountBuilder withEmailAddress(String emailAddress) {
            this.account.setEmailAddress(emailAddress);
            return this;
        }

        public AccountBuilder withPassword(String password) {
            this.account.setPassword(password);
            return this;
        }

        public AccountBuilder withAddress(String address) {
            this.account.setAddress(address);
            return this;
        }

        public AccountBuilder withPhoneNumber(String phoneNumber) {
            this.account.setPhoneNumber(phoneNumber);
            return this;
        }
    }
}