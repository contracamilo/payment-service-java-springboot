package com.payment_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Clase principal de la aplicación de Sistema de Gestión de Pagos Educativos.
 * Configura y arranca la aplicación Spring Boot y la documentación OpenAPI.
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Sistema de Gestión de Pagos Educativos",
        version = "1.0",
        description = "API para gestionar estudiantes y sus pagos"
    )
)
public class PaymentSystemApplication {
    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(PaymentSystemApplication.class, args);
    }
} 