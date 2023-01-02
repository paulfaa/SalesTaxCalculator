package org.paulfaa.Util;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.paulfaa.Util.RoundingUtil.roundUp;

public class RoundingUtilTest {

    @Test
    public void testRoundUp(){
        //act
        BigDecimal result = roundUp(BigDecimal.valueOf(54.625));

        //assert
        assertEquals(BigDecimal.valueOf(54.65), result);

    }

    @Test
    public void testRoundUpZero(){
        //act
        BigDecimal result = roundUp(BigDecimal.ZERO);

        //assert
        int matches = BigDecimal.ZERO.compareTo(result);
        assertEquals(0, matches);
    }
}
