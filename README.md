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

- Spring Web: Permite crear aplicaciones web, incluidas RESTful, utilizando Spring MVC. Utiliza Apache Tomcat como contenedor.
- Spring Data JPA: Persiste los datos en almacenes SQL con la API de persistencia de Java usando Spring Data e Hibernate.
- MySQL Driver: Controlador MySQL JDBC y R2DBC. Permite conectarnos a nuestra base de datos alojada en MySQL.

### Estructura
![estructura](https://user-images.githubusercontent.com/61515833/193315686-d6f37b9f-1c24-46ca-9403-3464ccda491e.png)

SpringBoot hace uso de anotaciones como `@Entity`, `@Autowired`, `@Controller`, etc. Pero que a ¿que se refieren? Pues son anotaciones de Java. Una anotación es un metadato que se incrusta en el código, se caracterizan por iniciar con el carácter @, este no cambia el comportamiento del código pero si puede indicarle al compilador acciones especificas de lectura o ejecución.

#### `/controllers`: Aqui se encuentran los controladores necesarios para manejar las solicitudes HTTP a nuestra aplicación.
```java
@Controller //Indica que la clase será un controller
@RequestMapping(path="/class") //Nos ayuda a mappear los HTTPRequest. Las URL inician con /class/ para POST, GET, PUT, DELETE 
public class ClassController {
    @Autowired //Sirve para poder acceder al archivo bean (generado automáicamente por spring) y manejar los datos (operaciones CRUD)
    private InstructorRepository instructorRepository;
    @Autowired 
    private ClassRepository classRepository;
    
    // @ResponseBody -> Es la respuesta de la solicitud HTTP 
    // @RequestParam -> Parámetros de la solicitud
    @PostMapping(path="/add") //Mapeo del método POST
    public @ResponseBody String addNewClass (@RequestParam String name,
      @RequestParam String description,
      @RequestParam String capacity,
      @RequestParam String idInstructor) {
      
        //Se instancia un objeto del modelo y se le asignan los prámetros recibidos para la inserción en la BD
        Class newClass = new Class(); 
        newClass.setName(name);
        newClass.setDescription(description);
        newClass.setCapacity(Integer.parseInt(capacity));
        
        Instructor instructor = instructorRepository.findById(Integer.parseInt(idInstructor)).get();
        newClass.setInstructor(instructor);

        classRepository.save(newClass); //Guardar en la BD
        return "Class Saved";
    }

    @GetMapping(path="/all") //Mapeo del método GET
    public @ResponseBody Iterable<Class> getAllClasses() {
        return classRepository.findAll(); //regresa todos los registros existentes en la tabla Class
    }
    
    // Aqui se recibe el parámetro id con ayuda de @PathVariable
    // @PathVariable se puede usar para manejar variables de plantilla en el mapeo de URI; extrae la parte con plantilla del URI, 
    //representada por la variable {id}
    @PutMapping(path="/changeClass/{id}") //Mapeo del método PUT
    public @ResponseBody String changeClass(@PathVariable int id, @RequestParam String name,
      @RequestParam String description,
      @RequestParam String capacity,
      @RequestParam String idInstructor) {
        
        Class updateClass = classRepository.findById(id).get(); // Buscamos el registro para actualizar sus datos     

        updateClass.setName(name);
        updateClass.setDescription(description);
        updateClass.setCapacity(Integer.parseInt(capacity));
       
        Instructor instructor = instructorRepository.findById(Integer.parseInt(idInstructor)).get();
        updateClass.setInstructor(instructor);
        classRepository.save(updateClass);

        return "Class updated";
    }
    
    @DeleteMapping(path="/deleteClass/{id}") //Mapeo del método DELETE
    public @ResponseBody String deleteClass(@PathVariable int id) {
        classRepository.deleteById(id); //elimina la clase mediante su id
        return "Class deleted";
    }
}

```

#### `/repositories`: Contiene los repositorios para cada una de las entidades de nuestros modelos. Spring implementa automáticamente esta interfaz de repositorio en un bean que tiene el mismo nombre (con un cambio en el caso: se llama classRepository). Gracias a essto podemos implementar operaciones CRUD sobre la entidad (modelo) `/models/Class.java`.
```java
package com.clubalpha.repositories;

import org.springframework.data.repository.CrudRepository;
import com.clubalpha.models.Class;

public interface ClassRepository extends CrudRepository<Class, Integer>{
    
}
```
#### `/models`: Modelos de las entidades de nuestra BD. Hibernate ([ORM](https://hibernate.org/) para JAVA ) traduce automáticamente la entidad a una tabla de la BD.
```java
package com.clubalpha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity //Esto le indica a Hibernate que debe hacer una tabla a partir de esta clase
@Table(name="classes") //Personalizar el nombre de la tabla, por defecto sería class
public class Class {
    @Id // Indica que el campo de miembro a continuación es la clave principal de la entidad actual.
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Anotación para configurar la forma de incremento de la columna (id)  
    // especificada. 
    private Integer idClass;
    private String name;  
    private String description;
    private Integer capacity;
    
    //Como esta es una tabla relacionada necesita especificar como es esa relación.
    @ManyToOne(fetch = LAZY) // Relación Muchos a uno (Muchas clases pueden ser impartidas por un Instructor)
    // los datos de Instructor no se inicializarán ni se cargarán en una memoria hasta que hagamos una llamada explícita. 
    // Por lo que usaremos JsonIgnore para ignorarlos en la solicitud GET
    @JsonIgnore
    @JoinColumn(name = "idInstructor", referencedColumnName="idInstructor") //se crea la columna `id_instructor` en la tabla 
    // `classes` y se referencia a `id_instructor` de la tabla `instructors`.
    private Instructor instructor; // aqui almacenará el id_instructor asignado
    
    // Por eso su get y set son de este tipo
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    // ... el resto de getters y setters
}
```
#### `ClubalphaApplication.java`: Es el archivo principal. Contiene el método main() que a su vez utiliza el método de Spring Boot SpringApplication.run() para iniciar la aplicación.

### Base de datos

Para hacer la conexión debemos establecer los parámetros en `resources/application.properties`

```editor-config
server.servlet.context-path=/clubalpha # Path de la aplicación 'http://localhost/8080/](http://localhost:8080/clubalpha.'
spring.jpa.hibernate.ddl-auto=update # Le indica a Hibernate que cambie la base de datos de acuerdo con las estructuras de entidad dadas.
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/sports_club # Base de datos
spring.datasource.username=springuser # Usuario
spring.datasource.password=ThePassword # Password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver # Driver MySQL
#spring.jpa.show-sql: true
```

![db_diagram](https://user-images.githubusercontent.com/61515833/193292873-8d7e46f8-49a9-4f07-baaf-a0fb2f542e57.png)

## Resultados

![update_member](https://user-images.githubusercontent.com/61515833/193293626-f4f96d08-39e9-40bc-bdb1-46f807e05012.gif)
