# servicio-auth

El servicio está diseñado para realizar el registro con usuario y contraseña. 
Después de esto, realiza la autentificación de contraseña buscando al usuario (findByuser) 
y compara la contraseña con (matches). 

El servicio responde con codigos:
- 200 (OK)
- 201 (Usuario creado)
- 400 (Bad Request)
- 401 (Error de autenticación)
- 409 (Conflict)

## Tecnologías

Lenguaje y framework:
- Java 25
- Spring Boot 4.1.1
- Maven, para manejar las dependencias

Capa de datos:
- MySQL 8
- Spring Data JPA / Hibernate
- MySQL Connector/J

Seguridad y validación:
- BCrypt - spring-security-crypto
- Bean Validation

Herramientas de desarrollo:
- Git y GitHub
- IntelliJ IDEA
- MySQL Workbench
- Postman

## Requisitos

JDK de Java. pom.xml(version 25).
MySQL 8, con la base auth_db creada.
Git
Postman, para probar los endpoints (o cualquier cliente HTTP).
Un IDE como IntelliJ, también se puede correr desde la terminal.

## Cómo ejecutarlo

1. Clonar el repositorio y entrar a la carpeta:

```powershell
   git clone https://github.com/dmejianovoa/GA7-220501096-AA5-EV01-Servicio-Web.git
   cd GA7-220501096-AA5-EV01-Servicio-Web
```

2. Crear la base de datos en MySQL (la tabla `users` la crea Hibernate al arrancar):

```sql
   CREATE DATABASE auth_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. Crear el archivo de configuración a partir del ejemplo:

```powershell
   Copy-Item src\main\resources\application.properties.example src\main\resources\application.properties
```

Abrir el archivo copiado y reemplazar `CONTRASEÑA12345` por la contraseña real de MySQL.

4. Arrancar la aplicación:

```powershell
   .\mvnw.cmd spring-boot:run
```

Cuando aparezca `Started AuthApplication`, el servicio queda en `http://localhost:8080`.

5. Probar los endpoints con Postman

## Endpoints

| Método | URL                  | Body | Respuestas                                                                                    |
|---|----------------------|---|-----------------------------------------------------------------------------------------------|
| POST | `/api/auth/register` | `{"user": "...", "password": "..."}` | 201 registrado, 409 ya existe, 400 datos inválidos                                            |
| POST | `/api/auth/login`    | `{"user": "...", "password": "..."}` | 200 autenticación satisfactoria, 401 error en la autenticación, 401 error en la autenticación |

## Estructura del proyecto

```src/main/java/co/edu/sena/auth/
├── controller/AuthController.java
├── service/AuthService.java
├── repository/UserRepository.java
├── model/User.java
├── dto/CredencialesDTO.java
└── AuthApplication.java
src/main/resources/application.properties.example
```
## Estándares de codificación

| Elemento | Regla | Ejemplo en el proyecto                        |
|---|---|-----------------------------------------------|
| Paquetes | Minúscula, sin guiones bajos | `co.edu.sena.auth.service`                    |
| Clases e interfaces | PascalCase | `AuthController`, `UserRepository`            |
| Métodos | camelCase, empiezan con un verbo | `register()`, `authenticate()`, `findByUser()` |
| Variables y atributos | camelCase | `passwordEncoder`, `userRepository`           |
| Comentarios | Cada clase lleva un comentario con su propósito, | Javadoc sobre `AuthService`                   |

Cada capa en su propio paquete (`controller`, `service`, `repository`, `model`, `dto`) Cada clase con su respectiva descripción. 

## Versionamiento

- Git local.
- GitHub remoto.

Documentos excluidos:
- ./application.properties
- ./target
