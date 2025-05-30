package com.payment_system.repository;

import com.payment_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByProgramaId(String programaId);
    List<Student> findByCodigo(String codigo);
} 