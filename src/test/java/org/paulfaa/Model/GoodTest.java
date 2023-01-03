package org.paulfaa.Model;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class GoodTest {

    public Good good;

    @Test
    public void testGetTotalValueSingleGood(){
        //arrange
        good = Good.builder()
                .name("Hat")
                .quantity(1)
                .value(new BigDecimal("12.95"))
                .type(GoodType.OTHER)
                .isImported(false)
                .build();

        //act
        BigDecimal result = good.getTotalValue();

        //assert
        assertEquals(new BigDecimal("12.95"), result);
    }

    @Test
    public void testGetTotalValueMultipleGoods(){
        //arrange
        good = Good.builder()
                .name("Shoe")
                .quantity(4)
                .value(new BigDecimal("30.50"))
                .type(GoodType.OTHER)
                .isImported(false)
                .build();

        //act
        BigDecimal result = good.getTotalValue();

        //assert
        assertEquals(new BigDecimal("122.00"), result);
    }
}
