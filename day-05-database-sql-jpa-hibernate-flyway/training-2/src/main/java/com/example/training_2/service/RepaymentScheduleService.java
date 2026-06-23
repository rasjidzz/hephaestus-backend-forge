package com.example.training_2.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.training_2.dto.RepaymentScheduleResponse;
import com.example.training_2.entity.LoanApplication;
import com.example.training_2.entity.RepaymentSchedule;
import com.example.training_2.entity.RepaymentScheduleStatus;
import com.example.training_2.repository.RepaymentScheduleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RepaymentScheduleService {
        @Value("${loan.interest.annual-rate}")
        private BigDecimal annualRate;

        private final RepaymentScheduleRepository repaymentScheduleRepository;

        public void generateSchedules(LoanApplication loanApplication) {
                log.info(
                                "Generating repayment schedules. loanId={}",
                                loanApplication.getId());
                // ambil loan amount
                BigDecimal loanAmount = loanApplication.getLoanAmount();

                // ambil tenor
                int tenor = loanApplication.getTenorMonth();

                // hitung monthly rate
                BigDecimal monthlyInterestRate = annualRate.divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_UP);

                // hitung principal
                BigDecimal principalAmount = loanAmount.divide(BigDecimal.valueOf(tenor), 2, RoundingMode.HALF_UP);

                // hitung interest
                BigDecimal interestAmount = loanAmount.multiply(monthlyInterestRate).setScale(2, RoundingMode.HALF_UP);

                // hitung total cicilan per bulan
                BigDecimal totalAmount = principalAmount.add(interestAmount);
                LocalDate dueDate = LocalDate.now();

                List<RepaymentSchedule> schedules = new ArrayList<>();

                // for tiap bulan {
                for (int installmentNumber = 1; installmentNumber <= tenor; installmentNumber++) {
                        // buat repayment schedule
                        RepaymentSchedule schedule = new RepaymentSchedule();

                        schedule.setLoanApplication(loanApplication);
                        // isi installment number
                        schedule.setInstallmentNumber(installmentNumber);
                        // isi principal
                        schedule.setPrincipalAmount(principalAmount);
                        // isi interest
                        schedule.setInterestAmount(interestAmount);
                        // isi total
                        schedule.setTotalAmount(totalAmount);
                        // isi due date
                        schedule.setDueDate(dueDate);

                        // status = UNPAID
                        schedule.setStatus(RepaymentScheduleStatus.UNPAID);

                        schedules.add(schedule);

                        dueDate = dueDate.plusMonths(1);
                }

                repaymentScheduleRepository.saveAll(schedules);
                log.info(
                                "Repayment schedules generated successfully. loanId={}",
                                loanApplication.getId());
        }

        private RepaymentScheduleResponse mapToResponse(
                        RepaymentSchedule repaymentSchedule) {

                return RepaymentScheduleResponse.builder()
                                .id(repaymentSchedule.getId())
                                .loanApplicationId(
                                                repaymentSchedule.getLoanApplication().getId())
                                .installmentNumber(
                                                repaymentSchedule.getInstallmentNumber())
                                .dueDate(repaymentSchedule.getDueDate())
                                .principalAmount(
                                                repaymentSchedule.getPrincipalAmount())
                                .interestAmount(
                                                repaymentSchedule.getInterestAmount())
                                .totalAmount(
                                                repaymentSchedule.getTotalAmount())
                                .status(repaymentSchedule.getStatus().toString())
                                .build();
        }

        public RepaymentScheduleResponse getById(Long id) {

                RepaymentSchedule repaymentSchedule = repaymentScheduleRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Repayment schedule not found"));

                return mapToResponse(repaymentSchedule);
        }
}
