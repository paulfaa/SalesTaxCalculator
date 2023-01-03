package org.paulfaa.Service;

import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;
import org.paulfaa.Model.Receipt;
import java.math.BigDecimal;

import static org.paulfaa.Util.RoundingUtil.roundUp;

public class CalculateService {

    final static BigDecimal SALES_TAX_RATE = new BigDecimal("0.10");
    final static BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");

    public void calculateTax(Good good){
        BigDecimal goodValue = good.getNetValue();
        BigDecimal salesTax = BigDecimal.ZERO;
        BigDecimal importTax = BigDecimal.ZERO;
        if (good.getType() == GoodType.OTHER){
            salesTax = calculateSalesTax(goodValue);
        }
        if (good.isImported()){
            importTax = calculateImportTax(goodValue);
        }
        good.getTax().setSalesTax(salesTax);
        good.getTax().setImportTax(importTax);
    }

    public Receipt generateReceipt(Good[] basket){
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal salesTax = BigDecimal.ZERO;
        for (Good good : basket) {
            calculateTax(good);
            BigDecimal goodCostIncludingTax = good.getGrossValue();
            totalCost = totalCost.add(goodCostIncludingTax);
            salesTax = salesTax.add(good.getTax().getTotalTax());
        }
        return new Receipt(basket, salesTax, totalCost);
    }

    private BigDecimal calculateSalesTax(BigDecimal goodValue){
        return roundUp(goodValue.multiply(SALES_TAX_RATE));
    }

    private BigDecimal calculateImportTax(BigDecimal goodValue){
        return roundUp(goodValue.multiply(IMPORT_TAX_RATE));
    }
}
