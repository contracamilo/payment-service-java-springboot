package com.payment_system.u_backend.dtos;

import java.time.LocalDate;
import com.payment_system.u_backend.emuns.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPaymentDTO {
    private double quantity;
    protected PaymentType typePayment;
    private LocalDate date;
    private String studentCode;

}
