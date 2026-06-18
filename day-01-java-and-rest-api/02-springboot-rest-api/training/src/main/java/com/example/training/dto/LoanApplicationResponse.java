package com.example.training.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.training.model.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationResponse {
    private UUID id;

    private UUID customerId;

    private BigDecimal loanAmount;

    private Integer tenorMonth;

    private String purpose;

    private LoanStatus status;
}
