package fr.uphf.etu.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidCodeException extends AppException {
    private static final long serialVersionUID = 1L;

    public InvalidCodeException() {
        super("Invalid secret code");
    }
}