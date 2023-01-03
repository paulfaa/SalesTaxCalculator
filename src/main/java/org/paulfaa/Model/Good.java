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
    private Tax tax;

    public Good(String name, int quantity, BigDecimal value, GoodType type, boolean isImported, Tax tax) {
        this.name = name;
        this.quantity = quantity;
        this.value = value;
        this.type = type;
        this.isImported = isImported;
        this.tax = tax;
    }

    public BigDecimal getNetValue(){
        return BigDecimal.valueOf(this.quantity).multiply(this.value);
    }

    public BigDecimal getGrossValue(){
        return this.getNetValue().add(this.tax.getTotalTax());
    }

    @Override
    public String toString() {
        String imported = "";
        if (this.isImported){
            imported = "imported ";
        }
        return this.getQuantity() + " " + imported + this.getName() + ": " + this.getGrossValue();
    }
}