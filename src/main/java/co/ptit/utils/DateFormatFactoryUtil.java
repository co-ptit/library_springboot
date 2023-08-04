package co.ptit.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/4/2023
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatFactoryUtil {

    private static Locale locale = new Locale("vi", "VN");

    public static DateFormat getDate(Locale locale) {
        return getDate(locale, null);
    }

    public static DateFormat getDate(Locale locale, TimeZone timeZone) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);

        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }

        return dateFormat;
    }

    public static DateFormat getDate(TimeZone timeZone) {
        return getDate(locale, timeZone);
    }

    public static DateFormat getDateTime(Locale locale) {
        return getDateTime(locale, null);
    }

    public static DateFormat getDateTime(Locale locale, TimeZone timeZone) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);

        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }

        return dateFormat;
    }

    public static DateFormat getDateTime(TimeZone timeZone) {
        return getDateTime(locale, timeZone);
    }

    public static DateFormat getSimpleDateFormat(String pattern) {
        return getSimpleDateFormat(pattern, locale, null);
    }

    public static DateFormat getSimpleDateFormat(String pattern, Locale locale) {
        return getSimpleDateFormat(pattern, locale, null);
    }

    public static DateFormat getSimpleDateFormat(String pattern, Locale locale, TimeZone timeZone) {

        DateFormat dateFormat = new SimpleDateFormat(pattern, locale);

        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }

        return dateFormat;
    }

    public static DateFormat getSimpleDateFormat(String pattern, TimeZone timeZone) {
        return getSimpleDateFormat(pattern, locale, timeZone);
    }

    public static DateFormat getTime(Locale locale) {
        return getTime(locale, null);
    }

    public static DateFormat getTime(Locale locale, TimeZone timeZone) {
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, locale);

        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }

        return dateFormat;
    }

    public static DateFormat getTime(TimeZone timeZone) {
        return getTime(locale, timeZone);
    }
}
