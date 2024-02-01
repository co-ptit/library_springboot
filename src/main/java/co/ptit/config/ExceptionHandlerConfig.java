package co.ptit.config;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.exception.ValidateCommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static co.ptit.utils.Constant.HTTP_ERROR_STATUS;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Slf4j
@Configuration
@ControllerAdvice
public class ExceptionHandlerConfig {

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

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return ResponseEntity.ok().body(ResponseDto.errBadRequest(fieldErrors.get(0).getDefaultMessage()));
    }

}
