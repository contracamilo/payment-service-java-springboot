package com.payment_system.service;

import com.payment_system.model.Payment;
import com.payment_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            payment.setStatus(status);
            return paymentRepository.save(payment);
        }
        return null;
    }

    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatus(status);
    }

    public List<Payment> getPaymentsByType(String type) {
        return paymentRepository.findByType(type);
    }

    public List<Payment> getPaymentsByStudentCodigo(String codigo) {
        return paymentRepository.findByEstudianteCodigo(codigo);
    }

    public Payment getPaymentByIdAndStatus(Long id, String status) {
        return paymentRepository.findById(id)
                .filter(payment -> payment.getStatus().equals(status))
                .orElse(null);
    }
} 