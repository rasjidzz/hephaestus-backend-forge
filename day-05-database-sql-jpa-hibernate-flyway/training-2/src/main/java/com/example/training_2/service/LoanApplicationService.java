package com.example.training_2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.training_2.dto.CreateLoanApplicationRequest;
import com.example.training_2.dto.CustomerSummaryResponse;
import com.example.training_2.dto.LoanApplicationResponse;
import com.example.training_2.dto.RepaymentScheduleResponse;
import com.example.training_2.dto.UpdateLoanStatusRequest;
import com.example.training_2.entity.Customer;
import com.example.training_2.entity.LoanApplication;
import com.example.training_2.entity.LoanApplicationStatus;
import com.example.training_2.entity.RepaymentSchedule;
import com.example.training_2.exception.NotFoundException;
import com.example.training_2.repository.CustomerRepository;
import com.example.training_2.repository.LoanApplicationRepository;
import com.example.training_2.repository.RepaymentScheduleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanApplicationService {
        private final LoanApplicationRepository loanApplicationRepository;
        private final RepaymentScheduleService repaymentScheduleService;
        private final CustomerRepository customerRepository;
        private final RepaymentScheduleRepository repaymentScheduleRepository;

        private LoanApplicationResponse mapToResponse(LoanApplication loanApplication) {

                Customer customer = loanApplication.getCustomer();

                CustomerSummaryResponse customerResponse = CustomerSummaryResponse.builder()
                                .id(customer.getId())
                                .fullName(customer.getFullName())
                                .nik(customer.getNik())
                                .email(customer.getEmail())
                                .build();

                return LoanApplicationResponse.builder()
                                .id(loanApplication.getId())
                                .loanAmount(loanApplication.getLoanAmount())
                                .tenorMonth(loanApplication.getTenorMonth())
                                .purpose(loanApplication.getPurpose())
                                .status(loanApplication.getStatus())
                                .customer(customerResponse)
                                .createdAt(loanApplication.getCreatedAt())
                                .updatedAt(loanApplication.getUpdatedAt())
                                .build();

        }

        @Transactional
        public LoanApplicationResponse create(
                        CreateLoanApplicationRequest request) {
                log.info(
                                "Creating loan application. customerId={}, loanAmount={}, tenor={}",
                                request.getCustomerId(),
                                request.getLoanAmount(),
                                request.getTenorMonth());

                // Customer customer = customerRepository.findById(
                // request.getCustomerId())
                // .orElseThrow(() -> new RuntimeException(
                // "Customer not found"));
                Customer customer = customerRepository.findById(request.getCustomerId())
                                .orElseThrow(() -> {
                                        log.warn("Customer not found. id={}", request.getCustomerId());
                                        return new NotFoundException("Customer not found");
                                });

                LoanApplication loanApplication = new LoanApplication();

                loanApplication.setCustomer(customer);

                loanApplication.setLoanAmount(
                                request.getLoanAmount());

                loanApplication.setTenorMonth(
                                request.getTenorMonth());

                loanApplication.setPurpose(
                                request.getPurpose());

                loanApplication.setStatus(
                                LoanApplicationStatus.SUBMITTED);

                LoanApplication savedLoan = loanApplicationRepository.save(
                                loanApplication);

                log.info(
                                "Loan application created successfully. loanId={}, customerId={}",
                                savedLoan.getId(),
                                customer.getId());

                return mapToResponse(savedLoan);
        }

        public List<LoanApplicationResponse> getAll(LoanApplicationStatus status) {
                log.debug("Getting loan applications. status={}", status);
                List<LoanApplication> result;

                if (status != null) {
                        result = loanApplicationRepository.findByStatus(status);
                } else {
                        result = loanApplicationRepository.findAll();
                }
                log.debug(
                                "Found {} loan applications",
                                result.size());
                List<LoanApplicationResponse> loanApplications = new ArrayList<>();
                for (LoanApplication loanApplication : result) {
                        loanApplications.add(mapToResponse(loanApplication));
                }
                return loanApplications;
        }

        public LoanApplicationResponse getById(Long id) {
                log.debug(
                                "Finding loan application by id={}",
                                id);
                LoanApplication loanApplication = loanApplicationRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Loan Application Not Found"));
                return mapToResponse(loanApplication);
        }

        @Transactional
        public LoanApplicationResponse patchStatus(
                        Long id,
                        UpdateLoanStatusRequest request) {
                LoanApplication loanApplication = loanApplicationRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Loan Application not found with id: " + id));

                LoanApplicationStatus oldStatus = loanApplication.getStatus();
                loanApplication.setStatus(request.getStatus());

                LoanApplication updatedLoanApplication = loanApplicationRepository.save(loanApplication);

                if (oldStatus != LoanApplicationStatus.APPROVED
                                && request.getStatus() == LoanApplicationStatus.APPROVED) {
                        log.info(
                                        "Updating loan status. loanId={}, oldStatus={}, newStatus={}",
                                        id,
                                        oldStatus,
                                        request.getStatus());
                        repaymentScheduleService.generateSchedules(loanApplication);
                        log.info(
                                        "Loan status updated successfully. loanId={}, status={}",
                                        updatedLoanApplication.getId(),
                                        updatedLoanApplication.getStatus());
                }

                return mapToResponse(updatedLoanApplication);
        }

        private RepaymentScheduleResponse mapScheduleToResponse(
                        RepaymentSchedule schedule) {

                RepaymentScheduleResponse response = new RepaymentScheduleResponse();

                response.setId(schedule.getId());

                response.setInstallmentNumber(
                                schedule.getInstallmentNumber());

                response.setPrincipalAmount(
                                schedule.getPrincipalAmount());

                response.setInterestAmount(
                                schedule.getInterestAmount());

                response.setTotalAmount(
                                schedule.getTotalAmount());

                response.setDueDate(
                                schedule.getDueDate());

                response.setStatus(
                                schedule.getStatus().toString());

                response.setLoanApplicationId(schedule.getLoanApplication().getId());

                return response;
        }

        public List<RepaymentScheduleResponse> getRepaymentSchedulesByLoanAppsId(
                        Long loanApplicationId) {
                log.debug(
                                "Getting repayment schedules. loanId={}",
                                loanApplicationId);

                LoanApplication loanApplication = loanApplicationRepository.findById(
                                loanApplicationId)
                                .orElseThrow(() -> new RuntimeException(
                                                "Loan Application not found"));

                List<RepaymentSchedule> schedules = repaymentScheduleRepository
                                .findByLoanApplicationId(loanApplicationId);

                return schedules.stream()
                                .map(this::mapScheduleToResponse)
                                .toList();
        }
}
