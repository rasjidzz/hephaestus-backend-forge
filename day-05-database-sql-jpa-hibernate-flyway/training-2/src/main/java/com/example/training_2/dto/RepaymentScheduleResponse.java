package com.example.training_2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.training_2.entity.RepaymentScheduleStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentScheduleResponse {

    private Long id;

    @JsonProperty("loan_application_id")
    private Long loanApplicationId;

    @JsonProperty("installment_number")
    private Integer installmentNumber;

    @JsonProperty("due_date")
    private LocalDate dueDate;

    @JsonProperty("principal_amount")
    private BigDecimal principalAmount;

    @JsonProperty("interest_amount")
    private BigDecimal interestAmount;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    private String status;
}
