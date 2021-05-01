package fr.uphf.etu.bank.payloads;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Operation request payload.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {
    /**
     * The account's id.
     */
    private int id;

    /**
     * The account's secret code.
     */
    @NotBlank
    private String code;

    /**
     * The merchant's name.
     */
    @NotBlank
    private String merchant;

    /**
     * The amount to deposit to/withdraw from the account. If the amount is
     * negative, the amount is withdrawn. Otherwise, it is deposited.
     */
    private double amount;
}
