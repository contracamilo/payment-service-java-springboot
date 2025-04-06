package com.payment_system.config;

import com.payment_system.model.Student;
import com.payment_system.model.Payment;
import com.payment_system.repository.StudentRepository;
import com.payment_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Componente encargado de cargar datos de prueba en la base de datos.
 * Se ejecuta automáticamente al iniciar la aplicación.
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Crea estudiantes y pagos de prueba en la base de datos.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    @Override
    public void run(String... args) {
        // Crear estudiantes de prueba
        List<Student> students = Arrays.asList(
            new Student(null, "Christian", "Ramirez", "1234", "LTA1", null),
            new Student(null, "Biaggio", "Ramirez", "12354", "LTA1", null),
            new Student(null, "Valentina", "Lopez", "5678", "LTA2", null)
        );

        studentRepository.saveAll(students);

        // Crear pagos de prueba
        Student christian = students.get(0);
        Payment payment1 = new Payment(null, LocalDate.of(2025, 3, 8), 16549.0, "CHEQUE", "CREADO", null, christian);
        Payment payment2 = new Payment(null, LocalDate.of(2025, 3, 8), 12000.0, "EFECTIVO", "PENDIENTE", null, christian);
        
        Student biaggio = students.get(1);
        Payment payment3 = new Payment(null, LocalDate.of(2025, 3, 9), 15000.0, "TRANSFERENCIA", "PAGADO", null, biaggio);
        
        Student valentina = students.get(2);
        Payment payment4 = new Payment(null, LocalDate.of(2025, 3, 10), 18000.0, "CHEQUE", "CREADO", null, valentina);

        paymentRepository.saveAll(Arrays.asList(payment1, payment2, payment3, payment4));
    }
} 