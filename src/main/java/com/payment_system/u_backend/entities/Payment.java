package com.payment_system.u_backend.entities;

import java.time.LocalDate;

import com.payment_system.u_backend.emuns.PaymentStatus;
import com.payment_system.u_backend.emuns.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private LocalDate date;
    private Double quantity;
    private PaymentType typePayment;
    private PaymentStatus statusPayment;
    private String file;

    @ManyToOne
    private Student student;

}
