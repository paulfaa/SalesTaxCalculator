package org.paulfaa;

import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;
import org.paulfaa.Service.CalculateService;
import java.math.BigDecimal;

import static org.paulfaa.Util.PrinterUtil.printReceipt;

public class Main {
    public static void main(String[] args) {

        CalculateService calculateService = new CalculateService();

        Good[] input1 = new Good[]{
                Good.builder()
                        .name("Book")
                        .quantity(1)
                        .value(new BigDecimal("12.49"))
                        .type(GoodType.BOOK)
                        .isImported(false)
                        .build(),
                Good.builder()
                        .name("Music CD")
                        .quantity(1)
                        .value(new BigDecimal("14.99"))
                        .type(GoodType.OTHER)
                        .isImported(false)
                        .build(),
                Good.builder()
                        .name("Chocolate Bar")
                        .quantity(1)
                        .value(new BigDecimal("0.85"))
                        .type(GoodType.FOOD)
                        .isImported(false)
                        .build()
        };
        Good[] input2 = new Good[]{
                Good.builder()
                        .name("Box of chocolates")
                        .quantity(1)
                        .value(new BigDecimal("10.00"))
                        .type(GoodType.FOOD)
                        .isImported(true)
                        .build(),
                Good.builder()
                        .name("Bottle of perfume")
                        .quantity(1)
                        .value(new BigDecimal("47.50"))
                        .type(GoodType.OTHER)
                        .isImported(true)
                        .build()
        };
        Good[] input3 = new Good[]{
                Good.builder()
                        .name("Bottle of perfume")
                        .quantity(1)
                        .value(new BigDecimal("27.99"))
                        .type(GoodType.OTHER)
                        .isImported(true)
                        .build(),
                Good.builder()
                        .name("Bottle of perfume")
                        .quantity(1)
                        .value(new BigDecimal("18.99"))
                        .type(GoodType.OTHER)
                        .isImported(false)
                        .build(),
                Good.builder()
                        .name("Packet of headache pills")
                        .quantity(1)
                        .value(new BigDecimal("9.75"))
                        .type(GoodType.MEDICAL)
                        .isImported(false)
                        .build(),
                Good.builder()
                        .name("Box of chocolates")
                        .quantity(1)
                        .value(new BigDecimal("11.25"))
                        .type(GoodType.FOOD)
                        .isImported(true)
                        .build()
        };
        Good[][] baskets = new Good[][]{
                input1,
                input2,
                input3
        };
        for (Good[] goods: baskets ){
            printReceipt(calculateService.generateReceipt(goods));
        }
    }
}