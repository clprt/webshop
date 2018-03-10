package com.company.webshop.mapper;

import com.company.webshop.domain.Account;
import com.company.webshop.dto.AccountDto;
import com.company.webshop.dto.CustomerDto;
import org.springframework.stereotype.Component;

import static com.company.webshop.domain.Account.AccountBuilder.account;
import static com.company.webshop.dto.CustomerDto.CustomerDtoBuilder.customerDto;

@Component
public class AccountMapper {

    public Account toAccount(AccountDto accountDto) {
        return account()
                .withFirstName(accountDto.getFirstName())
                .withLastName(accountDto.getLastName())
                .withEmailAddress(accountDto.getEmailAddress())
                .withPassword(accountDto.getPassword())
                .withAddress(accountDto.getAddress())
                .withPhoneNumber(accountDto.getPhoneNumber())
                .build();
    }

    public CustomerDto toCustomerDto(Account account) {
        return customerDto()
                .withCustomerId(account.getId())
                .withFirstName(account.getFirstName())
                .withLastName(account.getLastName())
                .withEmailAddress(account.getEmailAddress())
                .withAddress(account.getAddress())
                .withPhoneNumber(account.getPhoneNumber())
                .build();
    }
}
