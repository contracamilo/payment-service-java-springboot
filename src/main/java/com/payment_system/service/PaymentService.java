package com.payment_system.service;

import com.payment_system.model.Payment;
import com.payment_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo pago con ID autogenerado
     * 
     * @param payment El pago a crear (el ID debe ser null)
     * @return El pago creado con ID asignado
     */
    @Transactional
    public Payment createPayment(Payment payment) {
        // Aseguramos que el ID sea null para que se genere automáticamente
        if (payment.getId() != null) {
            log.warn("Se recibió un pago con ID: {}. Los IDs deben ser generados automáticamente. Estableciendo ID a null.", payment.getId());
            payment.setId(null);
        }
        
        return paymentRepository.save(payment);
    }

    @Transactional
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