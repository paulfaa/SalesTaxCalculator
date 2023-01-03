package org.paulfaa.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ReceiptTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
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
}
