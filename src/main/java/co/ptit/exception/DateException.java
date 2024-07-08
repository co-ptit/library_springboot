package co.ptit.exception;

import com.google.gson.JsonParseException;

/**
 * @author: HieuDo
 * @since: 7/8/2024
 * @project: library_springboot
 */

public class DateException extends JsonParseException {
    public DateException(String msg) {
        super(msg);
    }
}
