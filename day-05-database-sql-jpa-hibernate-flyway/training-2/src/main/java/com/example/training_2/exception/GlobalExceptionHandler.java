package com.example.training_2.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.training_2.dto.WebResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private String format(FieldError error) {
        return error.getField() + " " + error.getDefaultMessage();
    }

    private String correlationId() {
        return MDC.get("correlation_id");
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<WebResponse<Void>> badRequest(BadRequestException ex) {
        return ResponseEntity.badRequest().body(WebResponse.error("BAD_REQUEST", ex.getMessage(), null));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<WebResponse<Void>> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebResponse.error("NOT_FOUND", ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<Void>> generic(Exception ex) {
        log.error("event_name=unexpected_error correlation_id={} error_code=INTERNAL_SERVER_ERROR", correlationId());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(WebResponse.error("INTERNAL_SERVER_ERROR", "Unexpected error occurred", null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<Void>> validation(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(this::format)
                .collect(Collectors.toList());
        log.warn("event_name=validation_error correlation_id={} error_code=BAD_REQUEST", correlationId());
        return ResponseEntity.badRequest().body(WebResponse.error("BAD_REQUEST", "Validation failed", details));
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<WebResponse<?>> handleCustomerAlreadyExists(
            CustomerAlreadyExistsException ex) {

        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(WebResponse.error(
                        "CUSTOMER_ALREADY_EXISTS",
                        ex.getMessage()));
    }
}
