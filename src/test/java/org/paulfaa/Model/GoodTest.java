package org.paulfaa.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GoodTest {

    public Good good;

    @Test
    public void testGetTotalValueSingleGood(){
        //arrange
        good = Good.builder()
                .name("Hat")
                .quantity(1)
                .value(12.95)
                .type(GoodType.OTHER)
                .isImported(false)
                .build();

        //act
        double result = good.getTotalValue();

        //assert
        assertEquals(12.95, result, 0);
    }

    @Test
    public void testGetTotalValueMultipleGoods(){
        //arrange
        good = Good.builder()
                .name("Shoe")
                .quantity(4)
                .value(30.50)
                .type(GoodType.OTHER)
                .isImported(false)
                .build();

        //act
        double result = good.getTotalValue();

        //assert
        assertEquals(122.00, result, 0);
    }
}
