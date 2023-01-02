package org.paulfaa.Service;

import org.junit.Test;
import org.paulfaa.Model.Good;
import org.paulfaa.Model.GoodType;

import static org.junit.Assert.assertEquals;

public class CalculateServiceTest {

    public CalculateService classUnderTest = new CalculateService();

    @Test
    public void testCalculateCostNonImport(){
        //Arrange
        Good good = Good.builder()
                .name("Book")
                .quantity(1)
                .value(12.49)
                .type(GoodType.BOOK)
                .isImported(false)
                .build();

        //Act
        double result = classUnderTest.calculateCost(good);

        //Assert
        assertEquals(12.49, result, 0);
    }

    @Test
    public void testCalculateCostImport(){
        //Arrange
        Good good = Good.builder()
                .name("Box of chocolates")
                .quantity(1)
                .value(10.00)
                .type(GoodType.FOOD)
                .isImported(false)
                .build();

        //Act
        double result = classUnderTest.calculateCost(good);

        //Assert
        assertEquals(10.50, result, 0);
    }

    @Test
    public void testCalculateCostImportOther(){
        //Arrange
        Good good = Good.builder()
                .name("Bottle of perfume")
                .quantity(1)
                .value(47.50)
                .type(GoodType.OTHER)
                .isImported(true)
                .build();

        //Act
        double result = classUnderTest.calculateCost(good);

        //Assert
        assertEquals(54.65, result, 0);
    }
}
