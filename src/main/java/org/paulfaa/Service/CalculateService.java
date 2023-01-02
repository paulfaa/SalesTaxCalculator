package org.paulfaa.Service;

import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;
import java.math.BigDecimal;

import static org.paulfaa.Util.RoundingUtil.roundUp;

public class CalculateService {

    final static BigDecimal SALES_TAX_RATE = new BigDecimal("0.10");
    final static BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");

    public BigDecimal calculateCost(Good good){
        BigDecimal goodValue = good.getTotalValue();
        BigDecimal salesTax = BigDecimal.ZERO;
        BigDecimal importTax = BigDecimal.ZERO;
        if (good.getType() == GoodType.OTHER){
            salesTax = calculateSalesTax(goodValue);
        }
        if (good.isImported()){
            importTax = calculateImportTax(goodValue);
        }
        return goodValue.add(salesTax).add(importTax);
    }

    private BigDecimal calculateSalesTax(BigDecimal goodValue){
        return roundUp(goodValue.multiply(SALES_TAX_RATE));
    }

    private BigDecimal calculateImportTax(BigDecimal goodValue){
        return roundUp(goodValue.multiply(IMPORT_TAX_RATE));
    }

    public void calculateBasketCost(Good[] basket){
        BigDecimal overallCost = BigDecimal.ZERO;
        BigDecimal overallTax = BigDecimal.ZERO;
        for (Good good : basket) {
            BigDecimal costIncludingTax = calculateCost(good);
            overallCost = overallCost.add(costIncludingTax);
            overallTax = overallTax.add(costIncludingTax.subtract(good.getTotalValue()));
            System.out.println(good.getQuantity() + " " + good.getName() + " at " + costIncludingTax);
        }
        System.out.println("Sales Taxes: " + overallTax);
        System.out.println("Total: " + overallCost);
    }
}
