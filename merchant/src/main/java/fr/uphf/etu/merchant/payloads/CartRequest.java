package fr.uphf.etu.merchant.payloads;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class CartRequest {
    private int id;

    @Min(1)
    private int quantity = 1;

    @NoArgsConstructor
    public static class Add extends CartRequest {

    }

    @NoArgsConstructor
    public static class Remove extends CartRequest {

    }
}
