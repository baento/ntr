package fr.uphf.etu.merchant.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CheckoutRequest {
    private Integer id;

    private String code;
}
