package org.paulfaa.Service;

import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;

public class CalculateService {

    final static double SALES_TAX_RATE = 0.10;
    final static double IMPORT_TAX_RATE = 0.05;

    public double calculateCost(Good good){
        double goodValue = good.getTotalValue();
        double salesTax = 0;
        double importTax = 0;
        if (good.getType() == GoodType.OTHER){
            salesTax = calculateSalesTax(goodValue);
        }
        if (good.isImported()){
            importTax = calculateImportTax(goodValue);
        }
        return goodValue + salesTax + importTax;
    }

    private double calculateSalesTax(double goodValue){
        return goodValue * SALES_TAX_RATE;
    }

    private double calculateImportTax(double goodValue){
        return goodValue * IMPORT_TAX_RATE;
    }

    //public void calculateCost(Good[] basket){
        //todo
    //}
}
