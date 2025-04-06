package com.payment_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad que representa a un estudiante en el sistema.
 * Cada estudiante tiene un identificador único generado automáticamente como UUID.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    /** Identificador único del estudiante (UUID) */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    
    /** Nombre del estudiante */
    private String nombre;
    
    /** Apellido del estudiante */
    private String apellido;
    
    /** Código único asignado al estudiante */
    private String codigo;
    
    /** Identificador del programa académico al que pertenece */
    private String programaId;
    
    /** URL o ruta de la foto del estudiante */
    private String foto;
} 