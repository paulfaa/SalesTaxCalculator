package org.paulfaa.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Good {
    private String name;
    private int quantity;
    private BigDecimal value;
    private GoodType type;
    private boolean isImported;

    public Good(String name, int quantity, BigDecimal value, GoodType type, boolean isImported) {
        this.name = name;
        this.quantity = quantity;
        this.value = value;
        this.type = type;
        this.isImported = isImported;
    }

    public BigDecimal getTotalValue(){
        return BigDecimal.valueOf(this.quantity).multiply(this.value);
    }
}