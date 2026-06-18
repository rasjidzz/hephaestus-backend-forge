package com.example.training.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateLoanApplicationRequest {
    @NotNull
    private UUID customerId;

    @NotNull
    private BigDecimal loanAmount;

    @NotNull
    private Integer tenorMonth;

    @NotNull
    private String purpose;
}
