package org.paulfaa.Model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxTest {

    @Test
    public void testGetTotalTax(){
        //arrange
        Tax tax = new Tax(new BigDecimal("12.50"), new BigDecimal("10.50"));

        //act
        BigDecimal result = tax.getTotalTax();

        //assert
        assertEquals(new BigDecimal("23.0").compareTo(result), 0);
    }

    @Test
    public void testGetTotalTaxZero(){
        //arrange
        Tax tax = new Tax(new BigDecimal("12.50"), BigDecimal.ZERO);

        //act
        BigDecimal result = tax.getTotalTax();

        //assert
        assertEquals(new BigDecimal("12.50").compareTo(result), 0);
    }
}
