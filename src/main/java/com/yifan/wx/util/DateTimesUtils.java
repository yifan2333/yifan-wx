package com.yifan.wx.util;


import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author wuyifan
 * @since 2018年03月08日
 */
public class DateTimesUtils {

    public static final String FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间减去分钟数
     * @param date java.util.Date
     * @param min 分钟数
     * @return java.util.Date
     * @author wuyifan
     * @since 2018年3月8日
     */
    public static Date dateTimeMinusMinutes (Date date, long min) {

        if (Objects.isNull(date)) {
            return new Date();
        }

        LocalDateTime dateTime = dateToLocalDateTime(date);
        dateTime = dateTime.minusMinutes(min);
        return localTimeToDate(dateTime);
    }

    /**
     * 时间减去分钟数
     * @param date java.util.Date
     * @param pattern pattern 日期格式
     * @param min min 分钟数
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月8日
     */
    public static String dateTimeMinusMinutes (Date date, String pattern, long min) {

        if (Objects.isNull(date) || StringUtils.isBlank(pattern)) {
            return "";
        }

        DateTimeFormatter dt = getDateTimeFormatter(pattern);
        LocalDateTime dateTime = dateToLocalDateTime(date);
        dateTime = dateTime.minusMinutes(min);

        return dateTime.format(dt);
    }

    /**
     * 时间减去分钟数
     * @param dateTimeStr dateTimeStr
     * @param pattern pattern
     * @param min min
     * @return java.lang.String
     * @author wuyifan
     * @since 2018年3月8日
     */
    public static String dateTimeMinusMinutes (String dateTimeStr, String pattern, long min) {

        if (StringUtils.isBlank(dateTimeStr) || StringUtils.isBlank(pattern)) {
            return "";
        }

        DateTimeFormatter dt = getDateTimeFormatter(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dt);
        dateTime = dateTime.minusMinutes(min);

        return dateTime.format(dt);
    }

    /**
     * java.util.Date 转 LocalDateTime
     * @param date date
     * @return java.time.LocalDateTime
     * @author wuyifan
     * @since 2018年3月8日
     */
    private static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * LocalDateTime 转 java.util.Date
     * @param dateTime dateTime
     * @return java.util.Date
     * @author wuyifan
     * @since 2018年3月8日
     */
    private static Date localTimeToDate(LocalDateTime dateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = dateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    private static DateTimeFormatter getDateTimeFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static void main(String[] args) {
        String dateTimeStr = "2018-01-01 00:01:50";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.JANUARY, 1, 0, 1, 50);
        System.out.println(dateTimeMinusMinutes(dateTimeStr, FULL_PATTERN, 30));
        System.out.println(dateTimeMinusMinutes(calendar.getTime(), FULL_PATTERN, 30));
        System.out.println(dateTimeMinusMinutes(calendar.getTime(), 30));
    }

}
