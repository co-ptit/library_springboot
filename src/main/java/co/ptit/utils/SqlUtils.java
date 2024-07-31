package co.ptit.utils;

import org.springframework.util.ObjectUtils;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

public class SqlUtils {
    public static final String PERCENT = "%";

    private SqlUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String encodeAndReplaceKeyword(String param) {
        if (ObjectUtils.isEmpty(param))
            return null;

        String replace = param.replace("~", "\\~")
                .replace("_", "\\_")
                .replace("%", "\\%")
                .replace("*", "\\*")
                .trim().toLowerCase();

        return PERCENT + replace + PERCENT;
    }

}
