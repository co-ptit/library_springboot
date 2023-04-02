package co.ptit.config;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.exception.ValidateCommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.NestedServletException;

import static co.ptit.utils.Constant.HTTP_ERROR_STATUS;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Slf4j
@Configuration
@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, ValidateCommonException.class})
    protected ResponseEntity<Object> handleIllegalArgumentExceptions(RuntimeException ex) {
        log.error("Failed ValidateCommonException!", ex);
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HTTP_ERROR_STATUS).body(ResponseDto.err(bodyOfResponse));
    }

    @ExceptionHandler(value = {NestedServletException.class})
    protected ResponseEntity<Object> handleTimeOutNestedServletException(RuntimeException ex, WebRequest request) {
        log.error("Time out: {}", ((ServletWebRequest) request).getRequest().getRequestURI(), ex);
        throw ex;
    }

}
