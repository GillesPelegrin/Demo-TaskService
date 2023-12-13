package com.example.demo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateTimeWrapper {

    private static LocalDateTime instant;
    private static Duration offset;

    public static void pause() {
        setFixed(Instant.now());
    }

    public static void reset() {
        DateTimeWrapper.instant = null;
    }

    public static void setFixed(LocalDate localDate) {
        setFixed(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    public static void setFixed(Instant instant) {
        DateTimeWrapper.instant = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
        offset = null;
    }

    public static void setOffset(Duration duration) {
        offset = duration;
        instant = null;
    }

    public static LocalDateTime currentDateTime() {
        if (instant != null) {
            return instant;
        } else if (offset != null) {
            return LocalDateTime.now().plus(offset);
        } else {
            return LocalDateTime.now();
        }
    }
}
