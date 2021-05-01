package fr.uphf.etu.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientFundsException extends AppException {
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException() {
        super("Insufficient funds (no overdraft)");
    }
}