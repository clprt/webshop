package com.company.webshop.controller;

import com.company.webshop.domain.Account;
import com.company.webshop.dto.AccountDto;
import com.company.webshop.dto.CustomerDto;
import com.company.webshop.mapper.AccountMapper;
import com.company.webshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/customers")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDto accountDto) {
        Account savedAccount = accountService.createAccount(accountMapper.toAccount(accountDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAccount.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CustomerDto> retrieveAccount(@PathVariable("id") Long id) {
        CustomerDto customerDto = accountMapper.toCustomerDto(accountService.getAccountById(id));
        return ResponseEntity.ok(customerDto);
    }
}
