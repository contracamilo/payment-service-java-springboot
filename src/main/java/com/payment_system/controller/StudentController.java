package com.payment_system.controller;

import com.payment_system.model.Student;
import com.payment_system.model.Payment;
import com.payment_system.repository.StudentRepository;
import com.payment_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Estudiantes", description = "API para gestionar estudiantes")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/estudiantes")
    @Operation(summary = "Obtener todos los estudiantes")
    @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida correctamente")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/estudiantes/{codigo}")
    @Operation(summary = "Obtener un estudiante por código")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    public ResponseEntity<Student> getStudentByCodigo(@PathVariable String codigo) {
        Student student = studentRepository.findByCodigo(codigo);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping("/estudiantesPorPrograma/{programaId}")
    @Operation(summary = "Obtener estudiantes por programa")
    @ApiResponse(responseCode = "200", description = "Lista de estudiantes filtrada por programa")
    public List<Student> getStudentsByProgram(@PathVariable String programaId) {
        return studentRepository.findByProgramaId(programaId);
    }

    @GetMapping("/estudiantes/{codigo}/pagos")
    @Operation(summary = "Obtener pagos de un estudiante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos del estudiante"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    public List<Payment> getStudentPayments(@PathVariable String codigo) {
        return paymentRepository.findByEstudianteCodigo(codigo);
    }
} 