package org.fouda.repayment.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class MathUtilTest {
    private static final double DELTA = 1e-15;
    @Test
    public void testThatRoundingToTwoDecimalPlaces() {
        assertEquals(10.11d, MathUtil.round(10.11111d), DELTA);
        assertEquals(10d, MathUtil.round(10.0d), DELTA);
    }
}
