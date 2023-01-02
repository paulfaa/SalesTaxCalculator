package org.paulfaa.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Good {
    private String name;
    private int quantity;
    private double value;
    private GoodType type;
    private boolean isImported;

    public Good(String name, int quantity, double value, GoodType type, boolean isImported) {
        this.name = name;
        this.quantity = quantity;
        this.value = value;
        this.type = type;
        this.isImported = isImported;
    }

    public double getTotalValue(){
        return this.quantity * this.value;
    }
}