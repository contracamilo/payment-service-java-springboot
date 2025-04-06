package com.payment_system.controller;

import com.payment_system.model.Payment;
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
@Tag(name = "Pagos", description = "API para gestionar pagos")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/pagos")
    @Operation(summary = "Obtener todos los pagos")
    @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @PostMapping("/pagos")
    @Operation(summary = "Crear un nuevo pago")
    @ApiResponse(responseCode = "200", description = "Pago creado correctamente")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @GetMapping("/pagos/{id}")
    @Operation(summary = "Obtener un pago por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/pagos/{pagoId}/actualizarPago")
    @Operation(summary = "Actualizar estado de un pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Payment> updatePaymentStatus(
            @PathVariable Long pagoId,
            @RequestBody Payment paymentDetails) {
        return paymentRepository.findById(pagoId)
                .map(payment -> {
                    payment.setStatus(paymentDetails.getStatus());
                    return ResponseEntity.ok(paymentRepository.save(payment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pagosPorStatus")
    @Operation(summary = "Obtener pagos por estado")
    @ApiResponse(responseCode = "200", description = "Lista de pagos filtrada por estado")
    public List<Payment> getPaymentsByStatus(@RequestParam String status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping("/pagos/porTipo")
    @Operation(summary = "Obtener pagos por tipo")
    @ApiResponse(responseCode = "200", description = "Lista de pagos filtrada por tipo")
    public List<Payment> getPaymentsByType(@RequestParam String tipo) {
        return paymentRepository.findByType(tipo);
    }

    @GetMapping("/pagoFile/{pagoId}")
    @Operation(summary = "Obtener archivo de pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Archivo encontrado"),
        @ApiResponse(responseCode = "404", description = "Archivo no encontrado")
    })
    public ResponseEntity<String> getPaymentFile(@PathVariable Long pagoId) {
        return paymentRepository.findById(pagoId)
                .map(payment -> ResponseEntity.ok("Archivo del pago " + pagoId))
                .orElse(ResponseEntity.notFound().build());
    }
} 