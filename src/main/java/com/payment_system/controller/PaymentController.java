package com.payment_system.controller;

import com.payment_system.model.Payment;
import com.payment_system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@Tag(name = "Pagos", description = "API para gestionar pagos")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/pagos")
    @Operation(summary = "Obtener todos los pagos")
    @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping("/pagos")
    @Operation(summary = "Crear un nuevo pago")
    @ApiResponse(responseCode = "200", description = "Pago creado correctamente")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/pagos/{id}")
    @Operation(summary = "Obtener un pago por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/pagos/{id}/status")
    @Operation(summary = "Obtener un pago por ID y estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Payment> getPaymentByIdAndStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Payment payment = paymentService.getPaymentByIdAndStatus(id, status);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
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
        Payment payment = paymentService.updatePaymentStatus(pagoId, paymentDetails.getStatus());
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/pagosPorStatus")
    @Operation(summary = "Obtener pagos por estado")
    @ApiResponse(responseCode = "200", description = "Lista de pagos filtrada por estado")
    public List<Payment> getPaymentsByStatus(@RequestParam String status) {
        return paymentService.getPaymentsByStatus(status);
    }

    @GetMapping("/pagos/porTipo")
    @Operation(summary = "Obtener pagos por tipo")
    @ApiResponse(responseCode = "200", description = "Lista de pagos filtrada por tipo")
    public List<Payment> getPaymentsByType(@RequestParam String tipo) {
        return paymentService.getPaymentsByType(tipo);
    }

    @GetMapping("/pagoFile/{pagoId}")
    @Operation(summary = "Obtener archivo de pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Archivo encontrado"),
        @ApiResponse(responseCode = "404", description = "Archivo no encontrado")
    })
    public ResponseEntity<String> getPaymentFile(@PathVariable Long pagoId) {
        Payment payment = paymentService.getPaymentById(pagoId);
        return payment != null ? ResponseEntity.ok("Archivo del pago " + pagoId) : ResponseEntity.notFound().build();
    }
} 