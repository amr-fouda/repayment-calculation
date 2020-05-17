package org.fouda.repayment.utils;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private DateUtil() {
    }

    public static String formatDate(@NonNull LocalDateTime date) {
        return DateTimeFormatter.ISO_DATE_TIME.format(date.withMinute(0).withSecond(0).withNano(0).withHour(0));
    }
}
