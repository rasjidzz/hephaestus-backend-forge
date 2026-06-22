package com.example.training_2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.training_2.dto.CreatePaymentTransactionRequest;
import com.example.training_2.dto.PaymentTransactionResponse;
import com.example.training_2.dto.WebResponse;
import com.example.training_2.service.PaymentTransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment-transactions")
@RequiredArgsConstructor
public class PaymentTransactionController {
    private final PaymentTransactionService paymentTransactionService;

    @PostMapping()
    public WebResponse<PaymentTransactionResponse> create(
            @Valid @RequestBody CreatePaymentTransactionRequest request) {

        return WebResponse.success(
                "Successfully Create Payment Transaction",
                paymentTransactionService.create(
                        request));
    }
}
