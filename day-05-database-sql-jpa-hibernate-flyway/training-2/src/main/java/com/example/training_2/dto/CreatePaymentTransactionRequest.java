package com.example.training_2.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePaymentTransactionRequest {

    @NotNull(message = "Repayment schedule id is required")
    @JsonProperty("repayment_schedule_id")
    private Long repaymentScheduleId;

    @NotNull(message = "Paid amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Paid amount must be greater than 0")
    @Digits(integer = 17, fraction = 2, message = "Paid amount format is invalid")
    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    @JsonProperty("payment_reference")
    private String paymentReference;
}
