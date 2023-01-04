package org.paulfaa.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;
import org.paulfaa.Model.Receipt;
import org.paulfaa.Model.Tax;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.paulfaa.Util.PrinterUtil.printReceipt;
import static org.paulfaa.Util.PrinterUtil.printReceipts;

public class PrinterUtilTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Receipt receipt1;
    private Receipt receipt2;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        Good[] goods1 = new Good[]{
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
        Good[] goods2 = new Good[]{
                Good.builder()
                        .name("book")
                        .quantity(1)
                        .value(new BigDecimal("12.49"))
                        .type(GoodType.BOOK)
                        .isImported(false)
                        .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                        .build(),
                Good.builder()
                        .name("music CD")
                        .quantity(1)
                        .value(new BigDecimal("14.99"))
                        .type(GoodType.OTHER)
                        .isImported(false)
                        .tax(new Tax(new BigDecimal("1.50"), BigDecimal.ZERO))
                        .build(),
                Good.builder()
                        .name("chocolate bar")
                        .quantity(1)
                        .value(new BigDecimal("0.85"))
                        .type(GoodType.FOOD)
                        .isImported(false)
                        .tax(new Tax(BigDecimal.ZERO, BigDecimal.ZERO))
                        .build()
        };
        receipt1 = new Receipt(goods1, new BigDecimal("7.65"), new BigDecimal("65.15"));
        receipt2 = new Receipt(goods2, new BigDecimal("1.50"), new BigDecimal("29.83"));
    }

    @After
    public void tearDown() {
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

        //act
        printReceipt(receipt1);

        //assert
        assertEquals(expectedReponse, outContent.toString());
    }

    @Test
    public void testPrintReceipts(){
        //arrange
        Receipt[] receipts = new Receipt[]{
                receipt1,
                receipt2
        };
        String expectedReponse =
                """
                1 imported box of chocolates: 10.50\r
                1 imported bottle of perfume: 54.65\r
                Sales Taxes: 7.65\r
                Total: 65.15\r
                1 book: 12.49\r
                1 music CD: 16.49\r
                1 chocolate bar: 0.85\r
                Sales Taxes: 1.50\r
                Total: 29.83\r
                """;

        //act
        printReceipts(receipts);

        //assert
        assertEquals(expectedReponse, outContent.toString());
    }
}
