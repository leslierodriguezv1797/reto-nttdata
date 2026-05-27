# Reto NTT Data

Proyecto Spring Boot que consume la API publica de Random User y expone un endpoint REST para obtener una lista de 10 personas.

## Tecnologias

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Maven
- Lombok
- RestTemplate

## Funcionalidad implementada

Se implemento un flujo para consultar personas aleatorias desde `https://randomuser.me/api/` y devolver solo los atributos definidos en el DTO `PersonResponseDto`.

### DTO de respuesta

El DTO `PersonResponseDto` contiene los siguientes campos:

- `gender`: genero de la persona.
- `name`: lista con titulo, primer nombre y apellido.
- `location`: lista con numero de calle, nombre de calle, ciudad, estado, pais y codigo postal.
- `email`: correo electronico.
- `dob`: lista con fecha de nacimiento y edad.
- `picture`: lista con URLs de imagen en tamanos `large`, `medium` y `thumbnail`.

### Servicio

La clase `RandomUserServiceImpl` implementa `IRandomUserService`.

El metodo `getpersonlist()` realiza lo siguiente:

1. Consume la API externa `https://randomuser.me/api/?results=10`.
2. Lee la propiedad `results` de la respuesta.
3. Convierte cada resultado en un `PersonResponseDto`.
4. Retorna una lista con 10 personas.

### Controlador

La clase `PersonController` expone el servicio mediante un endpoint REST:

```http
GET /api/persons
```

La respuesta es una lista JSON de personas con la estructura del DTO.

Ejemplo de respuesta:

```json
[
  {
    "gender": "female",
    "name": ["Ms", "Jane", "Doe"],
    "location": ["123", "Main Street", "Bogota", "Cundinamarca", "Colombia", "110111"],
    "email": "jane.doe@example.com",
    "dob": ["1990-01-01T00:00:00.000Z", "36"],
    "picture": [
      "https://randomuser.me/api/portraits/women/1.jpg",
      "https://randomuser.me/api/portraits/med/women/1.jpg",
      "https://randomuser.me/api/portraits/thumb/women/1.jpg"
    ]
  }
]
```

## Ejecucion

Para compilar y ejecutar el proyecto se requiere tener configurado `JAVA_HOME` apuntando a un JDK 17.

Compilar:

```bash
./mvnw test
```

Ejecutar:

```bash
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Luego se puede consultar:

```http
http://localhost:8080/api/persons
```

## Estructura principal

```text
src/main/java/com/leslierodriguez/retonttdata
├── controller/PersonController.java
├── dto/PersonResponseDto.java
├── service/IRandomUserService.java
├── service/RandomUserServiceImpl.java
└── RetoNttdataApplication.java
```

## Nota de configuracion

Si Maven muestra el error `No compiler is provided in this environment`, significa que `JAVA_HOME` apunta a un JRE y debe cambiarse para apuntar a un JDK compatible con Java 17.
