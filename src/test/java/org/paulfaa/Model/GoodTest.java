package org.paulfaa.Model;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class GoodTest {

    public Good good;

    @Test
    public void testGetNetValueSingleGood(){
        //arrange
        good = Good.builder()
                .name("Hat")
                .quantity(1)
                .value(new BigDecimal("12.95"))
                .type(GoodType.OTHER)
                .isImported(false)
                .tax(new Tax(new BigDecimal("1.30"), new BigDecimal("0.65")))
                .build();

        //act
        BigDecimal result = good.getNetValue();

        //assert
        assertEquals(new BigDecimal("12.95"), result);
    }

    @Test
    public void testGetNetValueMultipleGoods(){
        //arrange
        good = Good.builder()
                .name("Shoe")
                .quantity(4)
                .value(new BigDecimal("30.50"))
                .type(GoodType.OTHER)
                .isImported(false)
                .tax(new Tax(new BigDecimal("12.20"), new BigDecimal("6.10")))
                .build();

        //act
        BigDecimal result = good.getNetValue();

        //assert
        assertEquals(new BigDecimal("122.00"), result);
    }

    @Test
    public void testGetTotalGrossValue(){
        //arrange
        good = Good.builder()
                .name("Glove")
                .quantity(2)
                .value(new BigDecimal("12.25"))
                .type(GoodType.OTHER)
                .isImported(false)
                .tax(new Tax(new BigDecimal("2.50"), new BigDecimal("1.25")))
                .build();

        //act
        BigDecimal result = good.getGrossValue();

        //assert
        assertEquals(new BigDecimal("28.25"), result);
    }

    @Test
    public void testToString(){
        //arrange
        Good good1 = Good.builder()
                .name("Apple")
                .quantity(1)
                .value(new BigDecimal("5.00"))
                .type(GoodType.FOOD)
                .isImported(false)
                .tax(new Tax(new BigDecimal("0.50"), BigDecimal.ZERO))
                .build();
        Good good2 = Good.builder()
                .name("Hat")
                .quantity(1)
                .value(new BigDecimal("10.00"))
                .type(GoodType.OTHER)
                .isImported(true)
                .tax(new Tax(new BigDecimal("1.00"), new BigDecimal("0.5")))
                .build();
        String expectedResponse1 = "1 Apple: 5.50";
        String expectedResponse2 = "1 imported Hat: 11.50";

        //act
        String actualResponse1 = good1.toString();
        String actualResponse2 = good2.toString();

        //assert
        assertEquals(expectedResponse1, actualResponse1);
        assertEquals(expectedResponse2, actualResponse2);
    }
}
