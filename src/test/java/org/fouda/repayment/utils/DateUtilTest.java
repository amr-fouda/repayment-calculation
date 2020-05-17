package org.fouda.repayment.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static java.time.LocalDateTime.of;
import static org.fouda.repayment.utils.DateUtil.formatDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class DateUtilTest {
    @Test
    public void testThatFormatDateIsUsingIsoDateFormat() {
        String formattedDate = formatDate(of(2020, 5, 1, 0, 0));
        assertNotNull(formattedDate);
        assertEquals("2020-05-01T00:00:00", formattedDate);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testThatFormatDateIsThrowingExceptionForNullInput() {
        formatDate(null);
    }
}
