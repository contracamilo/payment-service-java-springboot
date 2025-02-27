package com.payment_system.u_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment_system.u_backend.emuns.PaymentStatus;
import com.payment_system.u_backend.emuns.PaymentType;
import com.payment_system.u_backend.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByStudentCode(String studentCode);
    List<Payment> findByStatusPayment(PaymentStatus statusPayment);
    List<Payment> findByTypePayment(PaymentType typePayment);
}