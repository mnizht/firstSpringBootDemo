package com.example.demo.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * @author ZSP  2019/09/10 18:18
 */
public class Java8DateUtils {

    private Java8DateUtils() {
    }

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String REMOVE_SECOND_TIME_FORMAT = "HH:mm";


    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
    public static final DateTimeFormatter REMOVE_SECOND_FORMATTER = DateTimeFormatter.ofPattern(REMOVE_SECOND_TIME_FORMAT);


    /**
     * 字符串日期"yyyy-MM-dd"转LocalDate
     *
     * @author ZSP
     */
    public static LocalDate toLocalDate(String localDate) {
        return LocalDate.parse(localDate, DEFAULT_DATE_FORMATTER);
    }

    /**
     * LocalDate转字符串日期"yyyy-MM-dd"
     *
     * @author ZSP
     */
    public static String toString(LocalDate localDate) {
        return localDate.format(DEFAULT_DATE_FORMATTER);
    }


    /**
     * 字符串日期"yyyy-MM-dd HH:mm:ss"转LocalDateTime
     *
     * @author ZSP
     */
    public static LocalDateTime toLocalDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * 字符串时间"HH:mm:ss"转LocalTime
     */
    public static LocalTime toLocalTime(String localTime) {
        return LocalTime.parse(localTime, DEFAULT_TIME_FORMATTER);
    }

    /**
     * LocalDateTime转字符串日期"yyyy-MM-dd HH:mm:ss"
     *
     * @author ZSP
     */
    public static String toString(LocalDateTime localDateTime) {
        return localDateTime.format(DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * LocalDateTime 时间指定为00:00:00
     */
    public static LocalDateTime toTimeMin(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.with(LocalTime.MIN);
    }

    /**
     * LocalDateTime 时间指定为23:59:59.999999000
     * 由于目前mysql数据库设置了时间字段为 datetime(6),而java中 LocalTime小数点后是9位的纳秒值，存入数据库后会将
     * 第七位后的值四舍五入，所以这里最后三位写000和499 效果相同，一旦写大于等于500的值，整个时间就会进一秒
     */
    public static LocalDateTime toTimeMax(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.with(LocalTime.of(23, 59, 59, 999_999_000));
    }


    /**
     * 查询指定日期所在周的周一日期
     */
    public static LocalDate getMonday(LocalDate date) {
        return date.with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue());
    }

    /**
     * LocalTime 转时间 HH:mm
     */
    public static String toRemoveSecond(LocalTime time) {
        return time.format(REMOVE_SECOND_FORMATTER);
    }

    /**
     * LocalTime 转时间 HH:mm-HH:mm
     */
    public static String toRemoveSecond(LocalTime startTime, LocalTime endTime) {
        return String.format("%s-%s", startTime.format(REMOVE_SECOND_FORMATTER), endTime.format(REMOVE_SECOND_FORMATTER));
    }
}

