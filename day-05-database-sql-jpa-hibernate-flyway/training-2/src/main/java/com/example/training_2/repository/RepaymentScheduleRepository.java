package com.example.training_2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.training_2.entity.RepaymentSchedule;

public interface RepaymentScheduleRepository extends JpaRepository<RepaymentSchedule, Long> {
    List<RepaymentSchedule> findByLoanApplicationId(Long loanApplicationId);

    @Query("SELECT r FROM RepaymentSchedule r JOIN FETCH r.loanApplication WHERE r.id = :id")
    Optional<RepaymentSchedule> findByIdWithLoanApplication(@Param("id") Long id);
}