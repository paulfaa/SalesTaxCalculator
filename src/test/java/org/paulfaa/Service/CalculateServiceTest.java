package org.paulfaa.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculateServiceTest {

    public CalculateService classUnderTest = new CalculateService();
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
    public void testCalculateCostNonImport(){
        //Arrange
        Good good = Good.builder()
                .name("Book")
                .quantity(1)
                .value(BigDecimal.valueOf(12.49))
                .type(GoodType.BOOK)
                .isImported(false)
                .build();

        //Act
        BigDecimal result = classUnderTest.calculateCost(good);

        //Assert
        assertEquals(BigDecimal.valueOf(12.49).compareTo(result), 0);
    }

    @Test
    public void testCalculateCostNonImportOther(){
        //Arrange
        Good good = Good.builder()
                .name("Music CD")
                .quantity(1)
                .value(BigDecimal.valueOf(14.99))
                .type(GoodType.OTHER)
                .isImported(false)
                .build();

        //Act
        BigDecimal result = classUnderTest.calculateCost(good);

        //Assert
        assertEquals(BigDecimal.valueOf(16.49).compareTo(result), 0);
    }

    @Test
    public void testCalculateCostImport(){
        //Arrange
        Good good = Good.builder()
                .name("Box of chocolates")
                .quantity(1)
                .value(BigDecimal.valueOf(10.00))
                .type(GoodType.FOOD)
                .isImported(true)
                .build();

        //Act
        BigDecimal result = classUnderTest.calculateCost(good);

        //Assert
        assertEquals(BigDecimal.valueOf(10.50).compareTo(result), 0);
    }

    @Test
    public void testCalculateCostImportOther(){
        //Arrange
        Good good = Good.builder()
                .name("Bottle of perfume")
                .quantity(1)
                .value(BigDecimal.valueOf(47.50))
                .type(GoodType.OTHER)
                .isImported(true)
                .build();

        //Act
        BigDecimal result = classUnderTest.calculateCost(good);

        //Assert
        assertEquals(BigDecimal.valueOf(54.65).compareTo(result), 0);
    }

    @Test
    public void testCalculateBasketCost(){
        //arrange
        String expectedReponse =
                """
                1 Book at 12.49\r
                1 Music CD at 16.49\r
                1 Chocolate Bar at 0.85\r
                Sales Taxes: 1.50\r
                Total: 29.83\r
                """;

        Good[] basket = new Good[]{
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

        //act
        classUnderTest.calculateBasketCost(basket);

        //assert
        assertEquals(expectedReponse, outContent.toString());
    }
}
