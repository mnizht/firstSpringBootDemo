package com.example.demo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author haitao zhu
 * @date 2020/3/7 17:14
 */
public class DateTest {
    public static void main(String[] args) {
//        chronoUnitTest();
        dayOfWeekTest();
    }

    public static void dayOfWeekTest(){
        LocalDate now = LocalDate.now();
        System.out.println(now.getDayOfWeek().getValue());
    }

    public static void chronoUnitTest(){
        LocalDate start = LocalDate.parse("2020-02-27");
        LocalDate end = LocalDate.parse("2020-03-10");

        System.out.println(start);
        System.out.println(end);
        System.out.println(ChronoUnit.DAYS.between(start, end));
    }
}
