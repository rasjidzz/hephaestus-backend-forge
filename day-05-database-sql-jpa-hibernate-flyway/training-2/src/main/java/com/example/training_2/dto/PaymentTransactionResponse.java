package com.example.training_2.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTransactionResponse {

    private Long id;

    private Long repaymentScheduleId;

    private String paymentReference;

    private BigDecimal paidAmount;

    private LocalDateTime paidAt;

    private PaymentTransactionStatus status;

    private LocalDateTime createdAt;
}