package com.cyq.java8.datetime_java8.demo02;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * LocalDateTime演示
 */
public class LocalDateTimeTest01 {

    /**
     *  LocalDateTime   LocalDate   LocalTime
     */
    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 01, 12, 23, 34);
        System.out.println("LocalDateTime : " + localDateTime);

        System.out.println("Year : " + localDateTime.getYear());
        System.out.println("Month : " + localDateTime.getMonth().getValue());
        System.out.println("Day : " + localDateTime.getDayOfMonth());
        System.out.println("Hour : " + localDateTime.getHour());
        System.out.println("Minute : " + localDateTime.getMinute());
        System.out.println("second : " + localDateTime.getSecond());

        System.out.println("LocalDateTime add 2 years : " + localDateTime.plusYears(2));
        System.out.println("LocalDateTIme minus 2 days : " + localDateTime.minusDays(2));


        System.out.println("LocalDate : " + LocalDate.now());
        System.out.println("LocalTime : " + LocalTime.now());
    }

    /**
     * Instant : 时间戳，是从1970年1月1号 0点0分0秒到指定时间的毫秒值。
     */
    @Test
    public void test2() {
        Instant instant = Instant.now();    // 默认获取UTC时区，UTC叫做世界协调时间
        System.out.println("时间戳 : " + instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("偏移8个小时:" + offsetDateTime);

        long timeNum = instant.toEpochMilli();  // 获取毫秒值
        System.out.println("毫秒：" + timeNum);

        Instant instant1 = Instant.ofEpochMilli(1);
        System.out.println("加一秒：" + instant1);
    }

    /**
     *  Duration：计算两个时间之间的间隔的
     *  Period：计算两个日期之间的间隔的
     */
    @Test
    public void test3() throws InterruptedException {
        Instant beginTime = Instant.now();
        Thread.sleep(1000);
        Instant endTime = Instant.now();

        Duration duration = Duration.between(beginTime, endTime);
        System.out.println("秒间隔：" + duration.getSeconds());
        System.out.println("纳秒间隔：" + duration.getNano());

        System.out.println("--------------------------");

        LocalDate startDate = LocalDate.of(1020, 12, 23);
        LocalDate endDate = LocalDate.of(2021, 1, 23);
        Period period = Period.between(startDate, endDate);
        System.out.println("perriod：" + period);
        System.out.println("间隔天：" + period.getDays());
        System.out.println("间隔月：" + period.getMonths());
        System.out.println("间隔年：" + period.getYears());
    }

    /**
     * TemporalAdjuster：时间校验器
     */
    @Test
    public void test4() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime dateTime1 = localDateTime.withHour(23);
        System.out.println("修改小时为23：" + dateTime1);

        // TemporalAdjusters为TemporalAdjuster的工具类
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
        LocalDateTime dateTime2 = localDateTime.with(temporalAdjuster);
        System.out.println(localDateTime + " 第一个周一是:" + dateTime2);

        // 自定义TemporalAdjuster校验器(计算下一个工作日)
        LocalDateTime localDateTime2 = dateTime1.with(l -> {
            LocalDateTime localDateTime1 = (LocalDateTime) l;
            DayOfWeek dayOfWeek = localDateTime1.getDayOfWeek();
            if (DayOfWeek.FRIDAY.equals(dayOfWeek)) {
                return localDateTime1.plusDays(3);
            } else if (DayOfWeek.SATURDAY.equals(dayOfWeek)) {
                return localDateTime1.plusDays(2);
            } else {
                return localDateTime1.plusDays(1);
            }
        });

        System.out.println("自定义校验器：" + localDateTime2);

    }

    /**
     * DateTimeFormatter 时间格式化
     */
    @Test
    public void test5() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String time1 = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println("time1 : " + time1);

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String format2 = dateTimeFormatter1.format(LocalDate.now());
        System.out.println(format2);
        LocalDate localDate = LocalDate.parse(format2, dateTimeFormatter1);
        System.out.println(localDate);
    }

    /**
     * 获取时区支持的地区
     */
    @Test
    public void test6() {
        // 获取所有支持时区的地区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    /**
     * ZoneTime ZoneDate ZoneDateTime
     */
    @Test
    public void test7() {
        LocalDateTime time1 = LocalDateTime.now(ZoneId.of("America/Grand_Turk"));
        LocalDateTime time2 = LocalDateTime.now(ZoneId.of("America/Grand_Turk"));
        ZonedDateTime zonedDateTime = time2.atZone(ZoneId.of("America/Grand_Turk"));
        System.out.println(time1);
        System.out.println(zonedDateTime);
    }

}
