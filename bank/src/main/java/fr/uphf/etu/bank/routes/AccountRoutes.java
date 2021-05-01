package fr.uphf.etu.bank.routes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.uphf.etu.bank.exceptions.ResourceNotFoundException;
import fr.uphf.etu.bank.models.Account;
import fr.uphf.etu.bank.services.AccountService;

/**
 * Account REST routes. These routes only produce JSON.
 */
@RestController
@RequestMapping(path = "rest/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRoutes {
    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createAccount(@Validated @RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        return accountService.getAccount(id);
    }
}
