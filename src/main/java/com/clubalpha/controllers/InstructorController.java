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
import com.clubalpha.models.Instructor;
import com.clubalpha.repositories.InstructorRepository;

@Controller
@RequestMapping(path="/instructor")
public class InstructorController {
    @Autowired 
    private InstructorRepository instructorRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewInstructor (@RequestParam String name,
      @RequestParam String lastname,
      @RequestParam String academicDegree,
      @RequestParam String email,
      @RequestParam String dateOfBirth,
      @RequestParam String status,
      @RequestParam String address,
      @RequestParam String phoneNumber,
      @RequestParam String profileImg) {

        Instructor newInstructor = new Instructor();
        newInstructor.setName(name);
        newInstructor.setLastname(lastname);
        newInstructor.setAcademicDegree(academicDegree);
        newInstructor.setEmail(email);
        newInstructor.setDateOfBirth(dateOfBirth);
        newInstructor.setStatus(Integer.parseInt(status));
        newInstructor.setAddress(address);
        newInstructor.setPhoneNumber(phoneNumber);
        newInstructor.setProfileImg(profileImg);

        instructorRepository.save(newInstructor);
        return "Instructor Saved";
    }
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }
    
    @PutMapping(path="/changeInstructor/{id}")
    public @ResponseBody String changeInstructor(@PathVariable int id, @RequestParam String name,
      @RequestParam String lastname,
      @RequestParam String academicDegree,
      @RequestParam String email,
      @RequestParam String dateOfBirth,
      @RequestParam String status,
      @RequestParam String address,
      @RequestParam String phoneNumber,
      @RequestParam String profileImg) {
        
        Instructor updateInstructor = instructorRepository.findById(id).get();     

        updateInstructor.setName(name);
        updateInstructor.setLastname(lastname);
        updateInstructor.setAcademicDegree(academicDegree);
        updateInstructor.setEmail(email);
        updateInstructor.setDateOfBirth(dateOfBirth);
        updateInstructor.setStatus(Integer.parseInt(status));
        updateInstructor.setAddress(address);
        updateInstructor.setPhoneNumber(phoneNumber);
        updateInstructor.setProfileImg(profileImg);

        instructorRepository.save(updateInstructor);

        return "Instructor updated";
    }
    
    @DeleteMapping(path="/deleteInstructor/{id}")
    public @ResponseBody String deleteInstructor(@PathVariable int id) {
        instructorRepository.deleteById(id);
        return "Instructor deleted";
    }
}
