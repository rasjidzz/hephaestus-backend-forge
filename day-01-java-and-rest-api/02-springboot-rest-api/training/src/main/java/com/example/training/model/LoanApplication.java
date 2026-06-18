package com.example.training.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanApplication {
    private UUID id;

    private UUID customerId;

    private BigDecimal loanAmount;

    private Integer tenorMonth;

    private String purpose;

    private LoanStatus status;
}
