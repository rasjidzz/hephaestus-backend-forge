package com.example.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.training.dto.CreateLoanApplicationRequest;
import com.example.training.dto.LoanApplicationResponse;
import com.example.training.model.LoanApplication;
import com.example.training.model.LoanStatus;

@Service
public class LoanApplicationService {
    private final List<LoanApplication> loans = new ArrayList<>();
    
    public LoanApplication create(CreateLoanApplicationRequest request){
        LoanApplication loan = new LoanApplication(
            UUID.randomUUID(), 
            request.getCustomerId(),
             request.getLoanAmount(),
              request.getTenorMonth(),
               request.getPurpose(),
                LoanStatus.SUBMITTED
            );
        loans.add(loan);
        return loan;
    }

    public List<LoanApplicationResponse> findAll(String status, UUID customerUuid){
        List<LoanApplicationResponse> loanApplicationResponses = new ArrayList<>();

        for (LoanApplication loan : loans) {
            LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();
            if(status != null && !status.isEmpty()){
                if(loan.getStatus().toString().equals(status)){
                    loanApplicationResponse = toLoanApplicationResponse(loan);
                    loanApplicationResponses.add(loanApplicationResponse);
                }
            }else if (customerUuid != null){
                if(loan.getCustomerId() == customerUuid){
                    loanApplicationResponse = toLoanApplicationResponse(loan);
                    loanApplicationResponses.add(loanApplicationResponse);
                }
            }else{
                loanApplicationResponse = toLoanApplicationResponse(loan);
                loanApplicationResponses.add(loanApplicationResponse);
            }
        }
        return loanApplicationResponses;
    }

    // helper
    public LoanApplicationResponse toLoanApplicationResponse(LoanApplication loan){
        LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();
        loanApplicationResponse.setCustomerId(loan.getCustomerId());
        loanApplicationResponse.setId(loan.getId());
        loanApplicationResponse.setLoanAmount(loan.getLoanAmount());
        loanApplicationResponse.setPurpose(loan.getPurpose());
        loanApplicationResponse.setTenorMonth(loan.getTenorMonth());
        loanApplicationResponse.setStatus(loan.getStatus());
        return loanApplicationResponse;
    }

    public LoanApplication findById(UUID id){
        return loans.stream()
                .filter(loan ->
                        loan.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Loan not found"));
    }
     public LoanApplication approve(UUID id) {

        LoanApplication loan =
                findById(id);

        loan.setStatus(
                LoanStatus.APPROVED);

        return loan;
    }

    public LoanApplication reject(UUID id) {

        LoanApplication loan =
                findById(id);

        loan.setStatus(
                LoanStatus.REJECTED);

        return loan;
    }
}
