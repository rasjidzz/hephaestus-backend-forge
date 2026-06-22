package com.example.training_2.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.training_2.dto.CreatePaymentTransactionRequest;
import com.example.training_2.dto.PaymentTransactionResponse;
import com.example.training_2.dto.PaymentTransactionStatus;
import com.example.training_2.entity.PaymentTransaction;
import com.example.training_2.entity.RepaymentSchedule;
import com.example.training_2.entity.RepaymentScheduleStatus;
import com.example.training_2.repository.PaymentTransactionRepository;
import com.example.training_2.repository.RepaymentScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTransactionService {
    private final RepaymentScheduleRepository repaymentScheduleRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentTransactionResponse create(
            CreatePaymentTransactionRequest request) {

        RepaymentSchedule schedule = repaymentScheduleRepository
                .findById(
                        request.getRepaymentScheduleId())
                .orElseThrow(() -> new RuntimeException(
                        "Repayment Schedule not found"));

        PaymentTransaction transaction = new PaymentTransaction();

        transaction.setRepaymentSchedule(schedule);

        transaction.setPaymentReference(request.getPaymentReference());

        transaction.setPaidAmount(
                request.getPaidAmount());

        transaction.setPaidAt(
                LocalDateTime.now());

        transaction.setStatus(
                PaymentTransactionStatus.SUCCESS);

        PaymentTransaction saved = paymentTransactionRepository.save(
                transaction);

        updateRepaymentScheduleStatus(
                schedule.getId());

        return mapToResponse(saved);
    }

    private void updateRepaymentScheduleStatus(
            Long scheduleId) {

        RepaymentSchedule schedule = repaymentScheduleRepository
                .findById(scheduleId)
                .orElseThrow();

        BigDecimal totalPaid = paymentTransactionRepository
                .sumPaidAmountByScheduleId(
                        scheduleId);

        if (totalPaid.compareTo(
                BigDecimal.ZERO) == 0) {

            schedule.setStatus(
                    RepaymentScheduleStatus.UNPAID);

        } else if (totalPaid.compareTo(
                schedule.getTotalAmount()) >= 0) {

            schedule.setStatus(
                    RepaymentScheduleStatus.PAID);

        } else {

            schedule.setStatus(
                    RepaymentScheduleStatus.PARTIAL);
        }

        repaymentScheduleRepository.save(
                schedule);
    }

    private PaymentTransactionResponse mapToResponse(
            PaymentTransaction transaction) {

        PaymentTransactionResponse response = new PaymentTransactionResponse();

        response.setId(
                transaction.getId());

        response.setRepaymentScheduleId(
                transaction
                        .getRepaymentSchedule()
                        .getId());

        response.setPaymentReference(
                transaction.getPaymentReference());

        response.setPaidAmount(
                transaction.getPaidAmount());

        response.setPaidAt(
                transaction.getPaidAt());

        response.setStatus(
                transaction.getStatus());

        response.setCreatedAt(
                transaction.getCreatedAt());

        return response;
    }

}
