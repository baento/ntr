package fr.uphf.etu.bank.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.uphf.etu.bank.exceptions.InsufficientFundsException;
import fr.uphf.etu.bank.exceptions.InvalidCodeException;
import fr.uphf.etu.bank.exceptions.ResourceNotFoundException;
import fr.uphf.etu.bank.models.Account;
import fr.uphf.etu.bank.models.Operation;
import fr.uphf.etu.bank.payloads.OperationRequest;
import fr.uphf.etu.bank.repositories.AccountRepository;
import fr.uphf.etu.bank.repositories.OperationRepository;

@RestController
@RequestMapping(path = "rest/operations", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class OperationRoutes {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    /**
     * Tries to apply an operation to the account.
     * 
     * @param request the operation request
     * @throws ResourceNotFoundException  if there is no such account associated
     *                                    with the id
     * @throws InsufficientFundsException if the account doesn't have sufficient
     *                                    funds and doesn't allow overdraft
     */
    @PostMapping
    public void operationAccount(@Validated @RequestBody OperationRequest request)
            throws ResourceNotFoundException, InsufficientFundsException {
        Account account = accountRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", request.getId()));

        if (!account.getCode().equals(request.getCode())) {
            throw new InvalidCodeException();
        }

        if (!account.getOverdraftAllowed() && account.getBalance() - request.getAmount() < 0) {
            throw new InsufficientFundsException();
        }

        operationRepository.save(Operation.builder().account(account).merchant(request.getMerchant())
                .amount(-request.getAmount()).build());
    }
}
