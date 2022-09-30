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
#### `/models`: Modelos de las entidades de nuestra BD.
#### `ClubalphaApplication.java`: Es el archivo principal. Contiene el método main() que a su vez utiliza el método de Spring Boot SpringApplication.run() para iniciar la aplicación.

### Base de datos

![db_diagram](https://user-images.githubusercontent.com/61515833/193292873-8d7e46f8-49a9-4f07-baaf-a0fb2f542e57.png)

## Resultados

![update_member](https://user-images.githubusercontent.com/61515833/193293626-f4f96d08-39e9-40bc-bdb1-46f807e05012.gif)
