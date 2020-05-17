package org.fouda.repayment.utils;

public class MathUtil {
    private MathUtil() {
    }

    //Round by 2 decimal points
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
