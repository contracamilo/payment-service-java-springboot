package com.payment_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Entidad que representa un pago en el sistema.
 * Los pagos están asociados a un estudiante y tienen un estado que indica su progreso.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    /** Identificador único del pago (auto-incrementable) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /** Fecha en que se realizó el pago */
    private LocalDate fecha;
    
    /** Monto del pago */
    private Double monto;
    
    /** Tipo de pago (CHEQUE, EFECTIVO, TRANSFERENCIA) */
    private String type;
    
    /** Estado del pago (CREADO, PENDIENTE, PAGADO) */
    private String status;
    
    /** URL o ruta del archivo asociado al pago */
    private String file;
    
    /** Estudiante al que pertenece el pago */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student estudiante;
} 