package com.neo.util;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author taoyu
 * @date 2016年10月28日
 */
public final class DateUtils {

    public static final DateTimeFormatter LOCALDATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter LOCALDATETIME_FORMAT_MDHMS = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");

    public static final DateTimeFormatter LOCALDATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter LOCALDATETIME_FORMAT_YMDHS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static final DateTimeFormatter INT_LOCALDATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter INT_MMDD_FORMAT = DateTimeFormatter.ofPattern("MMdd");

    public static final DateTimeFormatter LOCALTIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final DateTimeFormatter LOCALDATETIME_FORMAT_YMD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");

    public static final DateTimeFormatter DOT_LOCALDATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private DateUtils() {
    }

    /**
     * 字符串转换成LocalTime
     *
     * @param timeStr
     * @return
     */
    public static LocalTime strToLocalTime(String timeStr) {
        return strToLocalTime(timeStr, LOCALTIME_FORMAT);
    }

    /**
     * 字符串转换成LocalTime
     *
     * @param timeStr
     * @param formatter
     * @return
     */
    public static LocalTime strToLocalTime(String timeStr, DateTimeFormatter formatter) {
        return LocalTime.parse(timeStr, formatter);
    }

    /**
     * 把字符串转化成LocalDate
     *
     * @param dateStr
     * @return
     */
    public static LocalDate strToLocalDate(String dateStr) {
        return strToLocalDate(dateStr, LOCALDATE_FORMAT);
    }

    /**
     * 把字符串转化成LocalDate
     *
     * @param dateStr
     * @param formatter
     * @return
     */
    public static LocalDate strToLocalDate(String dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * 把字符串转化成LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime strToLocalDateTime(String dateStr) {
        return strToLocalDateTime(dateStr, LOCALDATETIME_FORMAT);
    }

    /**
     * 把字符串转化成LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime strToLocalDateTime(String dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * 把LocalDate转化成字符串
     *
     * @param temporal
     * @return
     */
    public static String dateToStr(Temporal temporal) {
        return dateToStr(temporal, LOCALDATE_FORMAT);
    }

    /**
     * 把LocalDate转化成字符串
     *
     * @param temporal
     * @param formatter
     * @return
     */
    public static String dateToStr(Temporal temporal, DateTimeFormatter formatter) {
        return formatter.format(temporal);
    }

    /**
     *
     * @param date
     * @return
     */
    public static int getDay(LocalDateTime date) {
        return date.getDayOfMonth();
    }

    public static int getDay(LocalDate date) {
        return date.getDayOfMonth();
    }

    /**
     * 判断是否是日期
     *
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        return isDate(date, LOCALDATE_FORMAT);
    }

    /**
     * 判断是否是时间
     *
     * @param date
     * @return
     */
    public static boolean isDateTime(String date) {
        return isDate(date, LOCALDATETIME_FORMAT);
    }

    /**
     * 判断是否是日期
     *
     * @param date
     * @param format
     * @return
     */
    public static boolean isDate(String date, DateTimeFormatter format) {
        //avoid NullPointerException
        format.toString();
        try {
            format.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static int getMonth(LocalDate date) {
        return date.getMonthValue();
    }

    public static int getMonth(LocalDateTime date) {
        return date.getMonthValue();
    }

    public static int getYear(LocalDate date) {
        return date.getYear();
    }

    public static int getYear(LocalDateTime date) {
        return date.getYear();
    }

    public static boolean eq(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }

    public static boolean eq(LocalDateTime date1, LocalDateTime date2) {
        return date1.equals(date2);
    }

    public static boolean ne(LocalDate date1, LocalDate date2) {
        return !date1.equals(date2);
    }

    public static boolean ne(LocalDateTime date1, LocalDateTime date2) {
        return !date1.equals(date2);
    }

    public static boolean lt(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2) < 0;
    }

    public static boolean lt(LocalDateTime date1, LocalDateTime date2) {
        return date1.compareTo(date2) < 0;
    }

    public static boolean le(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2) <= 0;
    }

    public static boolean le(LocalDateTime date1, LocalDateTime date2) {
        return date1.compareTo(date2) <= 0;
    }

    public static boolean gt(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2) > 0;
    }

    public static boolean gt(LocalDateTime date1, LocalDateTime date2) {
        return date1.compareTo(date2) > 0;
    }

    public static boolean ge(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2) >= 0;
    }

    public static boolean ge(LocalDateTime date1, LocalDateTime date2) {
        return date1.compareTo(date2) >= 0;
    }

    public static boolean ge(LocalTime time1, LocalTime time2) {
        return time1.compareTo(time2) >= 0;
    }

    public static boolean le(LocalTime time1, LocalTime time2) {
        return time1.compareTo(time2) <= 0;
    }

    /**
     * 获取两个日期的月份数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenMonths(LocalDate startDate, LocalDate endDate) {
        Period pe = Period.between(startDate, endDate);
        return pe.getYears() * 12 + pe.getMonths();
    }

    /**
     * 获取两个日期的年份数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenYears(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getYears();
    }

    /**
     * 获取两个日期的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getBetweenDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 获取两个日期的天数
     * <p>
     * note: 如果两个日期天数太大（超过int最大值），结果将不准
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenDaysInt(LocalDate startDate, LocalDate endDate) {
        return getBetweenDays(startDate, endDate).intValue();
    }

    /**
     * 获取两个时间的秒数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getBetweenSeconds(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).getSeconds();
    }

    /**
     * 获取两个时间的分钟数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getBetweenMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.MINUTES.between(startTime, endTime);
    }

    /**
     * 获取两个时间小时数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getBetweenHours(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    /**
     * 添加月份
     *
     * @param startDate
     * @param month
     * @return
     */
    public static LocalDate addMonth(LocalDate startDate, int month) {
        return startDate.plusMonths(month);
    }

    /**
     * 添加月份
     *
     * @param startDate
     * @param month
     * @return
     */
    public static LocalDateTime addMonth(LocalDateTime startDate, int month) {
        return startDate.plusMonths(month);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param date
     * @return
     */
    public static LocalDate getSpecifiedDayBefore(LocalDate date) {
        return date.minusDays(1L);
    }

    /**
     * 获得指定日期的后一天
     *
     * @param date
     * @return
     */
    public static LocalDate getSpecifiedDayAfter(LocalDate date) {
        return date.plusDays(1L);
    }

    /**
     * 添加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    /**
     * 添加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static LocalDateTime addDays(LocalDateTime date, int days) {
        return date.plusDays(days);
    }


    /**
     * 添加小时数
     *
     * @param date
     * @param hour
     * @return
     */
    public static LocalDateTime addHours(LocalDateTime date, int hour) {
        return date.plusHours(hour);
    }

    /**
     * 获取当前日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return LocalDate.from(TemporalAdjusters.lastDayOfMonth().adjustInto(date));
    }

    /**
     * 获取当前日期所在月的第一天
     *
     * @param date
     * @return
     */
    public static LocalDate getFirstDayOfMonth(LocalDate date) {
        return LocalDate.from(TemporalAdjusters.firstDayOfMonth().adjustInto(date));
    }

    /**
     * 获取当前日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime date) {
        return LocalDateTime.from(TemporalAdjusters.lastDayOfMonth().adjustInto(date));
    }

    /**
     * 判断 compareTime 是否在 startTime，endTime 之间
     *
     * @param compareTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBetweenTimes(LocalTime compareTime, LocalTime startTime, LocalTime endTime) {
        return compareTime.compareTo(startTime) >= 0 && (compareTime.compareTo(endTime)) <= 0;
    }

    /**
     * 获取最小的日期
     *
     * @param date
     * @return
     */
    public static LocalDate min(LocalDate... date) {
        if (date.length == 1) {
            return date[0];
        } else if (date.length == 2) {
            return lt(date[0], date[1]) ? date[0] : date[1];
        }
        LocalDate min = date[0];
        for (int i = 1; i < date.length; i++) {
            if (lt(date[i], min)) {
                min = date[i];
            }
        }
        return min;
    }

    /**
     * 获取最大的日期
     *
     * @param date
     * @return
     */
    public static LocalDate max(LocalDate... date) {
        if (date.length == 1) {
            return date[0];
        } else if (date.length == 2) {
            return gt(date[0], date[1]) ? date[0] : date[1];
        }
        LocalDate max = date[0];
        for (int i = 1; i < date.length; i++) {
            if (gt(date[i], max)) {
                max = date[i];
            }
        }
        return max;
    }

    /**
     * 判断两个日期是否相隔正整数月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isWholeMonth(LocalDate startDate, LocalDate endDate) {
        int month = getBetweenMonths(startDate, endDate);
        if (month == 0) {
            month++;
        }
        return eq(addMonth(startDate, month), endDate);
    }

    /**
     * 对某时间添加分钟
     *
     * @param date    时间
     * @param minutes 分钟
     * @return
     */
    public static LocalDateTime addMinutes(LocalDateTime date, int minutes) {
        return date.plusMinutes(minutes);
    }

    public static LocalTime addMinutes(LocalTime time, int minutes) {
        return time.plusMinutes(minutes);
    }

    /**
     * XMLGregorianCalendar 转LocalDateTime
     *
     * @param xmlGregorianCalendar XMLGregorianCalendar
     * @return
     */
    public static LocalDateTime xmlGregorianCalendarToLocalDateTime(XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar == null) {
            return null;
        }
        Date date = xmlGregorianCalendar.toGregorianCalendar().getTime();
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


}
