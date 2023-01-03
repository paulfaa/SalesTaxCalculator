package org.paulfaa.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class RoundingUtil {

    public static BigDecimal roundUp(BigDecimal input){
        BigDecimal result = BigDecimal.valueOf(Math.ceil(input.doubleValue() * 20) / 20);
        return result.setScale(2, RoundingMode.UP);
    }
}
