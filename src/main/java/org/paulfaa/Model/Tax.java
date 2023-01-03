package org.paulfaa.Model;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class Tax {
    private BigDecimal salesTax;
    private BigDecimal importTax;

    public Tax(BigDecimal salesTax, BigDecimal importTax) {
        this.salesTax = salesTax;
        this.importTax = importTax;
    }

    public BigDecimal getTotalTax(){
        return this.salesTax.add(this.importTax);
    }
}
