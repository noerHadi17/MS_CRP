package com.wms.crp.exception;

import com.wms.crp.web.ApiResponseUtil;
import com.wms.crp.web.ResponseWrapper;
import com.wms.crp.i18n.I18nMessageCollection;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleValidation(MethodArgumentNotValidException ex, Locale locale) {
        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(err -> err instanceof FieldError fe ? fe.getField() + ": " + (fe.getDefaultMessage() == null ? "invalid" : fe.getDefaultMessage()) : (err.getDefaultMessage() == null ? "invalid" : err.getDefaultMessage()))
                .collect(Collectors.toList());
        log.debug("[crp] validation failed: {}", messages);
        // Return code to align with FE expectations
        return ResponseEntity.badRequest().body(ApiResponseUtil.error(I18nMessageCollection.QUESTIONNAIRE_INCOMPLETE.name(), String.join(", ", messages)));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleConstraint(ConstraintViolationException ex, Locale locale) {
        List<String> messages = ex.getConstraintViolations().stream().map(v -> v.getMessage()).collect(Collectors.toList());
        log.debug("[crp] constraint failed: {}", messages);
        return ResponseEntity.badRequest().body(ApiResponseUtil.error(I18nMessageCollection.ANSWER_INVALID.name(), String.join(", ", messages)));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleBusiness(BusinessException ex, Locale locale) {
        String code = ex.getMessageCode();
        String msg = messageSource.getMessage(code, null, code, locale);
        log.warn("[crp] business error code={} msg={}", code, msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseUtil.error(code, msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Void>> handleOther(Exception ex, Locale locale) {
        log.error("[crp] internal error", ex);
        String msg = messageSource.getMessage("INTERNAL_ERROR", null, "Unexpected", locale);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseUtil.error("INTERNAL_ERROR", msg));
    }
}
