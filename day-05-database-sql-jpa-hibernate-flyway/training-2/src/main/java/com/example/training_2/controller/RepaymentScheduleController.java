package com.example.training_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.training_2.dto.RepaymentScheduleResponse;
import com.example.training_2.dto.WebResponse;
import com.example.training_2.service.RepaymentScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/repayment-schedules")
@RequiredArgsConstructor
public class RepaymentScheduleController {
    private final RepaymentScheduleService repaymentScheduleService;

    @GetMapping("/{id}")
    public WebResponse<RepaymentScheduleResponse> getById(
            @PathVariable Long id) {

        return WebResponse.success("Successfylly Get By Id", repaymentScheduleService.getById(id));
    }
}
