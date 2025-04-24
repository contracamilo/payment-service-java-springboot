# Sistema de Gestión de Pagos Educativos

Este es un sistema backend desarrollado con Spring Boot para gestionar estudiantes y sus pagos en una institución educativa. El sistema proporciona una API RESTful con documentación Swagger UI.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL 16
- Swagger UI (SpringDoc OpenAPI)
- Maven
- Lombok

## Requisitos Previos

- Java JDK 21 o superior
- Maven 3.6 o superior
- Docker (para la base de datos PostgreSQL)

## Configuración y Ejecución

1. Clonar el repositorio:
```bash
git clone <url-del-repositorio>
cd u-backend
```

2. Configurar variables de entorno:
   - Copiar el archivo `.env.example` a `.env`
   - Modificar las variables en `.env` según tu entorno:
     ```
     DB_URL=jdbc:postgresql://localhost:5432/postgres
     DB_USERNAME=postgres
     DB_PASSWORD=your_password
     ```

3. Iniciar la base de datos PostgreSQL con Docker:
```bash
docker run --name sallepagos \
  -e POSTGRES_PASSWORD=your_password \
  -p 5432:5432 \
  -d postgres:16
```

4. Ejecutar la aplicación:
```bash
./mvnw spring-boot:run
```

5. Acceder a la documentación de la API:
```
http://localhost:8080/swagger-ui.html
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
   - GET `/estudiantes`
   - Respuesta: Lista de estudiantes

2. **Obtener estudiante por código**
   - GET `/estudiantes/{codigo}`
   - Respuesta: Estudiante individual

3. **Obtener estudiantes por programa**
   - GET `/estudiantesPorPrograma/{programaId}`
   - Respuesta: Lista de estudiantes del programa

4. **Obtener pagos de un estudiante**
   - GET `/estudiantes/{codigo}/pagos`
   - Respuesta: Lista de pagos del estudiante

#### Pagos

1. **Obtener todos los pagos**
   - GET `/pagos`
   - Respuesta: Lista de pagos

2. **Crear nuevo pago**
   - POST `/pagos`
   - Body: Objeto Payment
   - Respuesta: Pago creado

3. **Obtener pago por ID**
   - GET `/pagos/{id}`
   - Respuesta: Pago individual

4. **Obtener pago por ID y estado**
   - GET `/pagos/{id}/status?status=ESTADO`
   - Respuesta: Pago individual con estado específico

5. **Actualizar estado de pago**
   - PUT `/pagos/{pagoId}/actualizarPago`
   - Body: Objeto Payment con nuevo estado
   - Respuesta: Pago actualizado

6. **Obtener pagos por estado**
   - GET `/pagosPorStatus?status=ESTADO`
   - Respuesta: Lista de pagos filtrados por estado

7. **Obtener pagos por tipo**
   - GET `/pagos/porTipo?tipo=TIPO`
   - Respuesta: Lista de pagos filtrados por tipo

8. **Obtener archivo de pago**
   - GET `/pagoFile/{pagoId}`
   - Respuesta: Información del archivo de pago

## Variables de Entorno

El proyecto utiliza las siguientes variables de entorno:

- `DB_URL`: URL de conexión a la base de datos PostgreSQL
- `DB_USERNAME`: Usuario de la base de datos
- `DB_PASSWORD`: Contraseña de la base de datos

## Notas Adicionales

- La base de datos PostgreSQL se ejecuta en un contenedor Docker
- Todos los endpoints están documentados en Swagger UI
- La API utiliza CORS y permite solicitudes desde cualquier origen
- Los IDs de estudiantes se generan automáticamente como UUIDs
- Los IDs de pagos son números auto-incrementables 