package co.ptit.exception;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

public class ValidateCommonException extends IllegalArgumentException{

    public ValidateCommonException() {
    }

    public ValidateCommonException(String message) {
        super(message);
    }
}
