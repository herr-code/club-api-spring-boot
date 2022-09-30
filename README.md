# API Club

[![Spring Boot](https://img.shields.io/badge/SpringBoot-v2.74-success?style=for-the-badge&logo=springboot)](https://spring.io/)

Este proyecto contiene un API como servicio web para intercambiar datos entre cliente/servidor mediante HTTP Requests. Asi mismo se interactua con una base de datos relacional MySQL.

## Planteamiento del problema

Se requiere una servicio que nos permita crear horarios de un club deportivo. Las entidades y requerimientos son los siguientes:
- Instructor: Puede dar varias clases en distintos horarios.
- Clase
- Horario: Tiene varias clases.
- Asistentes: Pueden tomar más de una clase en más de un horario.

## Solucíón

Se opta por trabajar con Spring Boot que a su vez pertenece al framework Spring de Java. Esto nos va a permitir tener código menos acoplado y de fácil integración con otras tecnologías. Además nos simplifica la configuración inicial y la preparación de la aplicación para producción .

### Tecnologías
 
- [Apache Maven 3.8.5:](https://maven.apache.org/) Herramienta de software para la gestión y construcción de proyectos Java.
- [Spring Boot 2.74:](https://spring.io/) Proporciona un conjunto de herramientas para construir rápidamente aplicaciones de Spring fáciles de configurar.
- [Java 8:](https://www.java.com/es/) Plataforma informática de lenguaje de programación Java. Contiene importantes actualizaciones del lenguaje.
- [Postman:](https://www.postman.com/) Herramienta que permite testear colecciones o catálogos de APIs tanto para Frontend como para Backend.

Dependencias:

- Spring Web
- Spring Data JPA
- MySQL Driver

### Base de datos

![db_diagram](https://user-images.githubusercontent.com/61515833/193292873-8d7e46f8-49a9-4f07-baaf-a0fb2f542e57.png)

## Resultados

![update_member](https://user-images.githubusercontent.com/61515833/193293626-f4f96d08-39e9-40bc-bdb1-46f807e05012.gif)
