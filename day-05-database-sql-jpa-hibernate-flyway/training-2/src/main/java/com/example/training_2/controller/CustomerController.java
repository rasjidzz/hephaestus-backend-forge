package com.example.training_2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.training_2.dto.CreateCustomerRequest;
import com.example.training_2.dto.CustomerResponse;
import com.example.training_2.dto.LoanApplicationResponse;
import com.example.training_2.dto.PatchCustomerRequest;
import com.example.training_2.dto.WebResponse;
import com.example.training_2.service.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping()
    public WebResponse<CustomerResponse> create(@RequestBody CreateCustomerRequest entity) {
        return WebResponse.success("Successfully Add Data Customer", customerService.create(entity));
    }

    @GetMapping()
    public WebResponse<List<CustomerResponse>> getAll(@RequestParam(required = false) String full_name) {
        return WebResponse.success("Successfully Get All Data Customer", customerService.getAll(full_name));
    }

    @GetMapping("/{id}")
    public WebResponse<CustomerResponse> getById(@PathVariable Long id) {
        return WebResponse.success("Successfully Get Customer Data by Id", customerService.getById(id));
    }

    @PatchMapping("/{id}")
    public WebResponse<CustomerResponse> patchCustomer(@PathVariable Long id,
            @RequestBody PatchCustomerRequest request) {
        return WebResponse.success("Successfully Patch Data", customerService.patch(id, request));
    }

    @GetMapping("/{id}/loan-applications")
    public WebResponse<List<LoanApplicationResponse>> getLoanApplicationsByCustomerId(
            @PathVariable Long id) {

        return WebResponse.success(
                "Successfully Get Loan Applications By Customer Id",
                customerService.getLoanApplicationsByCustomerId(id));
    }

}
