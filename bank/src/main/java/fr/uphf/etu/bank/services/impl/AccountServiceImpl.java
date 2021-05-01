package fr.uphf.etu.bank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uphf.etu.bank.exceptions.ResourceNotFoundException;
import fr.uphf.etu.bank.models.Account;
import fr.uphf.etu.bank.repositories.AccountRepository;
import fr.uphf.etu.bank.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(int id) throws ResourceNotFoundException {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
    }

    public Integer createAccount(Account account) {
        return accountRepository.save(account).getId();
    }
}
