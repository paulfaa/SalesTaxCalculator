package org.paulfaa.Model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        final Receipt other = (Receipt) obj;
        return obj != null && obj.getClass() == this.getClass() && Arrays.equals(this.goods, other.goods)
                && this.salesTaxes.compareTo(other.salesTaxes) == 0 && this.total.compareTo(other.total) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(goods), salesTaxes, total);
    }
}
