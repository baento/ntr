package fr.uphf.etu.bank.models;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The account's owner.
     */
    @NaturalId
    @NotBlank
    private String owner;

    /**
     * The account's secret code.
     */
    @NotBlank
    private String code;

    /**
     * If true, operations that would result in a negative balance will still be
     * approved.
     */
    @Builder.Default
    private Boolean overdraftAllowed = false;

    /**
     * All operations associated with this account.
     */
    @OneToMany(mappedBy = "account")
    @JsonManagedReference("account")
    private final List<Operation> operations = Collections.emptyList();

    /**
     * Computes the current balance of this account by doing a sum of all
     * operations' amounts.
     * 
     * @return the balance of the account
     */
    public Double getBalance() {
        return operations.stream().mapToDouble(Operation::getAmount).sum();
    }
}
