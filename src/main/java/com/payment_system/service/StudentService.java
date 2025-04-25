package com.payment_system.service;

import com.payment_system.model.Student;
import com.payment_system.model.Payment;
import com.payment_system.repository.StudentRepository;
import com.payment_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByCodigo(String codigo) {
        List<Student> students = studentRepository.findByCodigo(codigo);
        if (students.isEmpty()) {
            return null;
        }
        if (students.size() > 1) {
            log.warn("Multiple students found for codigo {}. Returning the first one.", codigo);
            // Consider throwing an exception or implementing specific logic if duplicates are critical
        }
        return students.get(0);
    }

    public List<Student> getStudentsByPrograma(String programaId) {
        return studentRepository.findByProgramaId(programaId);
    }

    public Student createStudent(Student student) {
        // Consider adding logic to check if codigo already exists before saving
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(String codigo, Student studentDetails) {
        List<Student> students = studentRepository.findByCodigo(codigo);
         if (students.isEmpty()) {
            return null; // Or throw StudentNotFoundException
        }

        Student studentToUpdate;
        if (students.size() > 1) {
             log.warn("Multiple students found for codigo {}. Updating the first one found.", codigo);
             // Decide how to handle updates when duplicates exist. Updating only the first might hide data issues.
             studentToUpdate = students.get(0);
        } else {
             studentToUpdate = students.get(0);
        }

        studentToUpdate.setNombre(studentDetails.getNombre());
        studentToUpdate.setApellido(studentDetails.getApellido());
        studentToUpdate.setProgramaId(studentDetails.getProgramaId());
        studentToUpdate.setFoto(studentDetails.getFoto());
        // Note: This does not update the 'codigo' itself.
        return studentRepository.save(studentToUpdate);
    }

    @Transactional
    public void deleteStudent(String codigo) {
        log.info("Attempting to delete student(s) with codigo: {}", codigo);
        List<Student> studentsToDelete = studentRepository.findByCodigo(codigo);
        
        if (studentsToDelete.isEmpty()) {
            log.warn("No student found with codigo {} to delete.", codigo);
            return;
        }
        
        if (studentsToDelete.size() > 1) {
            log.warn("Found {} students with codigo {}. Will delete all of them.", studentsToDelete.size(), codigo);
        }
            
        try {
            // Para cada estudiante, eliminar primero sus pagos asociados
            for (Student student : studentsToDelete) {
                String studentId = student.getId();
                log.info("Processing student with ID: {}", studentId);
                
                try {
                    // Primero verificamos si hay pagos asociados
                    List<Payment> studentPayments = paymentRepository.findByEstudiante(student);
                    if (!studentPayments.isEmpty()) {
                        log.info("Found {} payments for student {}. Deleting them...", studentPayments.size(), studentId);
                        
                        // Eliminar todos los pagos asociados al estudiante
                        paymentRepository.deleteByEstudiante(student);
                        log.info("Successfully deleted all payments for student {}", studentId);
                    } else {
                        log.info("No payments found for student {}", studentId);
                    }
                    
                    // Ahora podemos eliminar el estudiante con seguridad
                    log.info("Deleting student with ID: {}", studentId);
                    studentRepository.delete(student);
                    log.info("Successfully deleted student with ID: {}", studentId);
                    
                } catch (Exception e) {
                    log.error("Error while deleting student with ID {}: {}", studentId, e.getMessage(), e);
                    throw e; // Re-throw to trigger transaction rollback
                }
            }
            
            log.info("Successfully completed deletion of {} student(s) with codigo {}", studentsToDelete.size(), codigo);
        } catch (Exception e) {
            log.error("Failed to delete students with codigo {}: {}", codigo, e.getMessage(), e);
            throw new RuntimeException("Failed to delete student(s) with codigo " + codigo, e);
        }
    }
} 