package com.payment_system.repository;

import com.payment_system.model.Payment;
import com.payment_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(String status);
    List<Payment> findByType(String type);
    List<Payment> findByEstudianteCodigo(String codigo);
    
    /**
     * Elimina todos los pagos asociados a un estudiante específico
     * @param estudiante El estudiante cuyos pagos serán eliminados
     */
    @Modifying
    @Query("DELETE FROM Payment p WHERE p.estudiante = :estudiante")
    void deleteByEstudiante(@Param("estudiante") Student estudiante);
    
    /**
     * Encuentra todos los pagos asociados a un estudiante específico
     * @param estudiante El estudiante cuyos pagos serán encontrados
     * @return Lista de pagos del estudiante
     */
    List<Payment> findByEstudiante(Student estudiante);
} 