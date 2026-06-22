package com.example.training_2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.training_2.entity.LoanApplication;
import com.example.training_2.entity.LoanApplicationStatus;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByStatusNot(LoanApplicationStatus status);

    List<LoanApplication> findByStatus(LoanApplicationStatus status);

    @Query("SELECT l FROM LoanApplication l JOIN FETCH l.customer WHERE l.id = :id")
    Optional<LoanApplication> findByIdWithCustomer(@Param("id") Long id);

    @Query("SELECT l FROM LoanApplication l JOIN l.customer c WHERE c.id = :customerId")
    List<LoanApplication> findLoansByCustomerId(@Param("customerId") Long customerId);
}
