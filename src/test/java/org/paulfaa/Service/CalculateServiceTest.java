package org.paulfaa.Service;

import org.junit.Test;
import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculateServiceTest {

    public CalculateService classUnderTest = new CalculateService();

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
}
