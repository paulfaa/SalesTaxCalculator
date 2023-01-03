package org.paulfaa.Model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Receipt {
    private Good[] goods;
    private BigDecimal salesTaxes;
    private BigDecimal total;

    public Receipt(Good[] goods, BigDecimal salesTaxes, BigDecimal total) {
        this.goods = goods;
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public void printReceipt(){
        for (Good good : this.goods) {
            System.out.println(good.toString());
        }
        System.out.println("Sales Taxes: " + this.salesTaxes);
        System.out.println("Total: " + this.total);
    }
}
