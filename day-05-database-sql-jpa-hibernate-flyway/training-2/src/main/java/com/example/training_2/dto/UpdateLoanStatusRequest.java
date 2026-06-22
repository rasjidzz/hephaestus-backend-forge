package com.example.training_2.dto;

import com.example.training_2.entity.LoanApplicationStatus;

import jakarta.validation.constraints.NotNull;

public class UpdateLoanStatusRequest {
    @NotNull(message = "Status is required")
    private LoanApplicationStatus status;

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(LoanApplicationStatus status) {
        this.status = status;
    }
}
