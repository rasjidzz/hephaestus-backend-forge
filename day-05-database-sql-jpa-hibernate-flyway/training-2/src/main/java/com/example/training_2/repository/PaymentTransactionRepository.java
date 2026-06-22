package com.example.training_2.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.training_2.entity.PaymentTransaction;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {

    List<PaymentTransaction> findByRepaymentScheduleId(Long repaymentScheduleId);

    @Query(value = "SELECT COALESCE(SUM(paid_amount), 0) FROM payment_transactions WHERE repayment_schedule_id = :scheduleId AND status = 'SUCCESS'", nativeQuery = true)
    BigDecimal sumPaidAmountByScheduleId(@Param("scheduleId") Long scheduleId);
}