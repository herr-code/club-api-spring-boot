package com.clubalpha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.clubalpha.models.Class;
import com.clubalpha.models.Instructor;
import com.clubalpha.repositories.ClassRepository;
import com.clubalpha.repositories.InstructorRepository;

@Controller
@RequestMapping(path="/class")
public class ClassController {
    @Autowired 
    private InstructorRepository instructorRepository;
    @Autowired 
    private ClassRepository classRepository;
    

    @PostMapping(path="/add")
    public @ResponseBody String addNewClass (@RequestParam String name,
      @RequestParam String description,
      @RequestParam String capacity,
      @RequestParam String idInstructor) {

        Class newClass = new Class();
        newClass.setName(name);
        newClass.setDescription(description);
        newClass.setCapacity(Integer.parseInt(capacity));
        
        Instructor instructor = instructorRepository.findById(Integer.parseInt(idInstructor)).get();
        newClass.setInstructor(instructor);

        classRepository.save(newClass);
        return "Class Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Class> getAllClasses() {
        return classRepository.findAll();
    }
    
    @PutMapping(path="/changeClass/{id}")
    public @ResponseBody String changeClass(@PathVariable int id, @RequestParam String name,
      @RequestParam String description,
      @RequestParam String capacity,
      @RequestParam String idInstructor) {
        
        Class updateClass = classRepository.findById(id).get();     

        updateClass.setName(name);
        updateClass.setDescription(description);
        updateClass.setCapacity(Integer.parseInt(capacity));
       
        Instructor instructor = instructorRepository.findById(Integer.parseInt(idInstructor)).get();
        updateClass.setInstructor(instructor);
        classRepository.save(updateClass);

        return "Class updated";
    }
    
    @DeleteMapping(path="/deleteClass/{id}")
    public @ResponseBody String deleteClass(@PathVariable int id) {
        classRepository.deleteById(id);
        return "Class deleted";
    }
}
