package com.yl.soft.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static String dateTimeToString(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(dateTime);
    }

    public static LocalDateTime stringToDateTime(String dateTimeStr, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, dtf);
    }

    public static LocalDateTime secondToDateTime(Integer second) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
    }

    public static LocalDateTime toDayStart(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 0, 0, 0);
    }

    public static LocalDateTime toDayEnd(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 23, 59, 59);
    }

}
