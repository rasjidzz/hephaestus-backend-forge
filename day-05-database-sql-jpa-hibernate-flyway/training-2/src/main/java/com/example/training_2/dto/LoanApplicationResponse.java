package com.example.training_2.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.example.training_2.entity.LoanApplicationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanApplicationResponse {
    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("loan_amount")
    private BigDecimal loanAmount;

    @JsonProperty("tenor_month")
    private Integer tenorMonth;

    private String purpose;

    private LoanApplicationStatus status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}