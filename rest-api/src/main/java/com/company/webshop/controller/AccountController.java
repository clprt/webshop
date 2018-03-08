package com.company.webshop.controller;

import com.company.webshop.repository.AccountRepository;
import com.company.webshop.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequestMapping("/api/customers")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
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
    public ResponseEntity<Account> retrieveAccount(@PathVariable("id") Long id) {
        Account account = accountRepository.findById(id).get();
        return ResponseEntity.ok(account);
    }

}
