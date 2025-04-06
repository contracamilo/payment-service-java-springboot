package com.payment_system.repository;

import com.payment_system.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(String status);
    List<Payment> findByType(String type);
    List<Payment> findByEstudianteCodigo(String codigo);
} 