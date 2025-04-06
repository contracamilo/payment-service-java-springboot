# Sistema de Gestión de Pagos Educativos

Este es un sistema backend desarrollado con Spring Boot para gestionar estudiantes y sus pagos en una institución educativa. El sistema proporciona una API RESTful con documentación Swagger UI.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database (base de datos en memoria)
- Swagger UI (SpringDoc OpenAPI)
- Maven
- Lombok

## Requisitos Previos

- Java JDK 21 o superior
- Maven 3.6 o superior

## Configuración y Ejecución

1. Clonar el repositorio:
```bash
git clone <url-del-repositorio>
cd u-backend
```

2. Ejecutar la aplicación:
```bash
./mvnw spring-boot:run
```

3. Acceder a la documentación de la API:
```
http://localhost:8080/swagger-ui.html
```

4. Acceder a la consola H2 (base de datos):
```
http://localhost:8080/h2-console
URL JDBC: jdbc:h2:mem:testdb
Usuario: sa
Contraseña: <vacía>
```

## Estructura del Proyecto

### Modelos

#### Student (Estudiante)
- `id`: String (UUID generado automáticamente)
- `nombre`: String
- `apellido`: String
- `codigo`: String
- `programaId`: String
- `foto`: String

#### Payment (Pago)
- `id`: Long (auto-incrementable)
- `fecha`: LocalDate
- `monto`: Double
- `type`: String
- `status`: String
- `file`: String
- `estudiante`: Referencia al estudiante

### Endpoints

#### Estudiantes

1. **Obtener todos los estudiantes**
   - GET `/api/estudiantes`
   - Respuesta: Lista de estudiantes

2. **Obtener estudiante por código**
   - GET `/api/estudiantes/{codigo}`
   - Respuesta: Estudiante individual

3. **Obtener estudiantes por programa**
   - GET `/api/estudiantesPorPrograma/{programaId}`
   - Respuesta: Lista de estudiantes del programa

4. **Obtener pagos de un estudiante**
   - GET `/api/estudiantes/{codigo}/pagos`
   - Respuesta: Lista de pagos del estudiante

#### Pagos

1. **Obtener todos los pagos**
   - GET `/api/pagos`
   - Respuesta: Lista de pagos

2. **Crear nuevo pago**
   - POST `/api/pagos`
   - Body: Objeto Payment
   - Respuesta: Pago creado

3. **Obtener pago por ID**
   - GET `/api/pagos/{id}`
   - Respuesta: Pago individual

4. **Actualizar estado de pago**
   - PUT `/api/pagos/{pagoId}/actualizarPago`
   - Body: Objeto Payment con nuevo estado
   - Respuesta: Pago actualizado

5. **Obtener pagos por estado**
   - GET `/api/pagosPorStatus?status=ESTADO`
   - Respuesta: Lista de pagos filtrados por estado

6. **Obtener pagos por tipo**
   - GET `/api/pagos/porTipo?tipo=TIPO`
   - Respuesta: Lista de pagos filtrados por tipo

7. **Obtener archivo de pago**
   - GET `/api/pagoFile/{pagoId}`
   - Respuesta: Información del archivo de pago

## Datos de Prueba

La aplicación incluye datos de prueba que se cargan automáticamente al iniciar:

### Estudiantes de ejemplo:
- Christian Ramirez (Código: 1234, Programa: LTA1)
- Biaggio Ramirez (Código: 12354, Programa: LTA1)
- Valentina Lopez (Código: 5678, Programa: LTA2)

### Pagos de ejemplo:
- Pago por cheque de $16,549 para Christian
- Pago en efectivo de $12,000 para Christian
- Transferencia de $15,000 para Biaggio
- Pago por cheque de $18,000 para Valentina

## Estados de Pago
- CREADO
- PENDIENTE
- PAGADO

## Tipos de Pago
- CHEQUE
- EFECTIVO
- TRANSFERENCIA

## Notas Adicionales

- La base de datos H2 se reinicia cada vez que se reinicia la aplicación
- Todos los endpoints están documentados en Swagger UI
- La API utiliza CORS y permite solicitudes desde cualquier origen
- Los IDs de estudiantes se generan automáticamente como UUIDs
- Los IDs de pagos son números auto-incrementables 