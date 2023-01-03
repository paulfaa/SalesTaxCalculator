package org.paulfaa.Service;

import org.junit.Test;
import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;
import org.paulfaa.Model.Receipt;
import org.paulfaa.Model.Tax;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculateServiceTest {

    public CalculateService classUnderTest = new CalculateService();

    @Test
    public void testCalculateTax(){
        //Arrange
        Good good = Good.builder()
                .name("Book")
                .quantity(1)
                .value(new BigDecimal("12.49"))
                .type(GoodType.BOOK)
                .isImported(false)
                .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                .build();

        //Act
        assertEquals(new BigDecimal("0").compareTo(good.getTax().getTotalTax()), 0);
        classUnderTest.calculateTax(good);

        //Assert
        assertEquals(new BigDecimal("12.49").compareTo(good.getNetValue()), 0);
        assertEquals(BigDecimal.ZERO.compareTo(good.getTax().getTotalTax()), 0);
    }

    @Test
    public void testCalculateTaxNonImportOther(){
        //Arrange
        Good good = Good.builder()
                .name("Music CD")
                .quantity(1)
                .value(new BigDecimal("14.99"))
                .type(GoodType.OTHER)
                .isImported(false)
                .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                .build();

        //Act
        assertEquals(new BigDecimal("0").compareTo(good.getTax().getTotalTax()), 0);
        classUnderTest.calculateTax(good);

        //Assert
        System.out.println("net value: " + good.getNetValue());
        assertEquals(new BigDecimal("16.49").compareTo(good.getGrossValue()), 0);
        assertEquals(new BigDecimal("1.50").compareTo(good.getTax().getTotalTax()), 0);
    }

    @Test
    public void testCalculateTaxImport(){
        //Arrange
        Good good = Good.builder()
                .name("Box of chocolates")
                .quantity(1)
                .value(new BigDecimal("10.00"))
                .type(GoodType.FOOD)
                .isImported(true)
                .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                .build();

        //Act
        assertEquals(BigDecimal.ZERO.compareTo(good.getTax().getTotalTax()), 0);
        classUnderTest.calculateTax(good);

        //Assert
        assertEquals(new BigDecimal("0.50").compareTo(good.getTax().getTotalTax()), 0);
        assertEquals(new BigDecimal("10.50").compareTo(good.getGrossValue()), 0);
    }

    @Test
    public void testCalculateTaxImportOther(){
        //Arrange
        Good good = Good.builder()
                .name("Bottle of perfume")
                .quantity(1)
                .value(new BigDecimal("47.50"))
                .type(GoodType.OTHER)
                .isImported(true)
                .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                .build();

        //Act
        assertEquals(BigDecimal.ZERO.compareTo(good.getTax().getTotalTax()), 0);
        classUnderTest.calculateTax(good);

        //Assert
        assertEquals(new BigDecimal("7.15").compareTo(good.getTax().getTotalTax()), 0);
        assertEquals(new BigDecimal("54.65").compareTo(good.getGrossValue()), 0);
    }

    @Test
    public void testGenerateReceipt(){
        //arrange
        Good[] basket = new Good[]{
                Good.builder()
                        .name("box of chocolates")
                        .quantity(1)
                        .value(new BigDecimal("10.00"))
                        .type(GoodType.FOOD)
                        .isImported(true)
                        .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                        .build(),
                Good.builder()
                        .name("bottle of perfume")
                        .quantity(1)
                        .value(new BigDecimal("47.50"))
                        .type(GoodType.OTHER)
                        .isImported(true)
                        .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                        .build()
        };
        Receipt expectedReceipt = new Receipt(basket, new BigDecimal("7.65"), new BigDecimal("65.15"));

        //act
        Receipt actualReceipt = classUnderTest.generateReceipt(basket);

        //assert
        assertEquals(expectedReceipt, actualReceipt);
    }
}
