package com.example.training_2.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoanApplicationRequest {
    @NotNull(message = "Customer id is required")
    @JsonProperty("customer_id")
    private Long customerId;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "1.00", message = "Loan amount must be greater than 0")
    @JsonProperty("loan_amount")
    private BigDecimal loanAmount;

    @Positive(message = "Tenor month must be greater than 0")
    @JsonProperty("tenor_month")
    private Integer tenorMonth;

    private String purpose;
}
