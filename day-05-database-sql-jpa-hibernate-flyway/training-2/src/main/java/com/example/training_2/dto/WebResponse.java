package com.example.training_2.dto;

import java.util.List;

import org.slf4j.MDC;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String error;
    private List<String> details;
    private long timestamp;
    private String correlationId;

    public static <T> WebResponse<T> success(String message, T data) {
        return WebResponse.<T>builder().success(true)
                .message(message)
                .data(data)
                .timestamp(System.currentTimeMillis())
                .correlationId(MDC.get("correlation_id"))
                .build();
    }

    public static <T> WebResponse<T> error(String message, String error) {
        return WebResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .timestamp(System.currentTimeMillis())
                .correlationId(MDC.get("correlation_id"))
                .build();
    }

    public static <T> WebResponse<T> error(String message, String error, List<String> details) {
        return WebResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .details(details)
                .timestamp(System.currentTimeMillis())
                .correlationId(MDC.get("correlation_id"))
                .build();
    }
}