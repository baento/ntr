package fr.uphf.etu.bank.services;

import java.util.List;

import fr.uphf.etu.bank.exceptions.ResourceNotFoundException;
import fr.uphf.etu.bank.models.Account;

/**
 * Account service
 */
public interface AccountService {
    /**
     * Gets all accounts.
     * 
     * @return a list of all accounts
     */
    public List<Account> getAllAccounts();

    /**
     * Gets the account with id. May throw an exception if the account is not found.
     * 
     * @param id the account id
     * @return the account associated with the id
     * @throws ResourceNotFoundException if there is no such account associated with
     *                                   the id
     */
    public Account getAccount(int id) throws ResourceNotFoundException;

    /**
     * Creates an account.
     * 
     * @param account the account info
     * @return the id of the newly created account
     */
    public Integer createAccount(Account account);
}
