package com.payment_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import java.time.LocalDate;

/**
 * Entidad que representa un pago en el sistema.
 * Los pagos están asociados a un estudiante y tienen un estado que indica su progreso.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED) // Protegido para evitar uso incorrecto
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
    
    /**
     * Constructor para crear nuevos pagos sin ID (el ID será generado por la base de datos)
     * 
     * @param fecha Fecha del pago
     * @param monto Monto del pago
     * @param type Tipo de pago
     * @param status Estado del pago
     * @param file URL o ruta del archivo
     * @param estudiante Estudiante asociado
     */
    public Payment(LocalDate fecha, Double monto, String type, String status, String file, Student estudiante) {
        this.fecha = fecha;
        this.monto = monto;
        this.type = type;
        this.status = status;
        this.file = file;
        this.estudiante = estudiante;
    }
} 