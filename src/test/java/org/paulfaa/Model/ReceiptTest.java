package org.paulfaa.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReceiptTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
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
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintReceipt(){
        //arrange
        String expectedReponse =
                """
                1 imported box of chocolates: 10.50\r
                1 imported bottle of perfume: 54.65\r
                Sales Taxes: 7.65\r
                Total: 65.15\r
                """;
        Good[] goods = new Good[]{
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
        Receipt receipt = new Receipt(goods, new BigDecimal("7.65"), new BigDecimal("65.15"));

        //act
        receipt.printReceipt();

        //assert
        assertEquals(expectedReponse, outContent.toString());
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
}
