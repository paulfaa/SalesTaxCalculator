package org.paulfaa.Model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReceiptTest {

    private Good[] goods;

    @Before
    public void setUp() {
        goods = new Good[]{
                Good.builder()
                        .name("box of chocolates")
                        .quantity(1)
                        .value(new BigDecimal("10.00"))
                        .type(GoodType.FOOD)
                        .isImported(true)
                        .tax(new Tax(BigDecimal.ZERO, new BigDecimal("0.50")))
                        .build(),
                Good.builder()
                        .name("bottle of perfume")
                        .quantity(1)
                        .value(new BigDecimal("47.50"))
                        .type(GoodType.OTHER)
                        .isImported(true)
                        .tax(new Tax(new BigDecimal("4.75"), new BigDecimal("2.40")))
                        .build()
        };
    }

    @Test
    public void testEquals(){
        //arrange
        Receipt receipt1 = new Receipt(goods, BigDecimal.TEN, BigDecimal.ZERO);
        Receipt receipt2 = new Receipt(goods, BigDecimal.TEN, BigDecimal.ZERO);

        //act
        boolean result = receipt1.equals(receipt2);

        //assert
        assertEquals(receipt1.getSalesTaxes(), receipt2.getSalesTaxes());
        assertEquals(receipt1.getTotal(), receipt2.getTotal());
        assertTrue(result);
    }

    @Test
    public void testEqualsNegative(){
        //arrange
        Receipt receipt1 = new Receipt(goods, BigDecimal.ONE, BigDecimal.TEN);
        Receipt receipt2 = new Receipt(goods, BigDecimal.TEN, BigDecimal.ZERO);

        //act
        boolean result = receipt1.equals(receipt2);

        //assert
        assertNotEquals(receipt1.getSalesTaxes(), receipt2.getSalesTaxes());
        assertNotEquals(receipt1.getTotal(), receipt2.getTotal());
        assertFalse(result);
    }

    @Test
    public void testHashCode(){
        //arrange
        Receipt receipt1 = new Receipt(goods, BigDecimal.TEN, BigDecimal.ZERO);
        Receipt receipt2 = new Receipt(goods, BigDecimal.TEN, BigDecimal.ZERO);

        //act
        int result1 = receipt1.hashCode();
        int result2 = receipt2.hashCode();

        //assert
        assertEquals(result1, result2);
    }

    @Test
    public void testHashCodeNegative(){
        //arrange
        Receipt receipt1 = new Receipt(goods, BigDecimal.ONE, BigDecimal.TEN);
        Receipt receipt2 = new Receipt(goods, BigDecimal.TEN, BigDecimal.ZERO);

        //act
        int result1 = receipt1.hashCode();
        int result2 = receipt2.hashCode();

        //assert
        assertNotEquals(result1, result2);
    }
}
