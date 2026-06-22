package com.example.training_2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.training_2.dto.CreateLoanApplicationRequest;
import com.example.training_2.dto.LoanApplicationResponse;
import com.example.training_2.dto.RepaymentScheduleResponse;
import com.example.training_2.dto.UpdateLoanStatusRequest;
import com.example.training_2.dto.WebResponse;
import com.example.training_2.entity.LoanApplicationStatus;
import com.example.training_2.service.LoanApplicationService;
import com.example.training_2.service.RepaymentScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/loan-applications")
@RequiredArgsConstructor
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;
    private final RepaymentScheduleService repaymentScheduleService;

    @GetMapping()
    public WebResponse<List<LoanApplicationResponse>> getAll(
            @RequestParam(required = false) LoanApplicationStatus status) {
        return WebResponse.success("Successfully Get All Data Loan Application", loanApplicationService.getAll(status));
    }

    @PostMapping()
    public WebResponse<LoanApplicationResponse> create(
            @RequestBody CreateLoanApplicationRequest request) {

        return WebResponse.success(
                "Successfully Create Loan Application",
                loanApplicationService.create(request));
    }

    @GetMapping("/{id}")
    public WebResponse<LoanApplicationResponse> getById(@PathVariable Long id) {
        return WebResponse.success("Successfully get data by id", loanApplicationService.getById(id));
    }

    @PatchMapping("/{id}/status")
    public WebResponse<LoanApplicationResponse> patchStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLoanStatusRequest request) {
        LoanApplicationResponse response = loanApplicationService.patchStatus(id, request);

        return WebResponse.success("Successfully edit status", response);
    }

    @GetMapping("/{loanApplicationId}/repayment-schedules")
    public WebResponse<List<RepaymentScheduleResponse>> getRepaymentSchedulesByLoanAppsId(
            @PathVariable Long loanApplicationId) {

        return WebResponse.success(
                "Successfully Get Repayment Schedules",
                loanApplicationService.getRepaymentSchedulesByLoanAppsId(loanApplicationId));
    }

}
