package com.clubalpha.repositories;

import org.springframework.data.repository.CrudRepository;
import com.clubalpha.models.Instructor;

public interface InstructorRepository extends CrudRepository<Instructor, Integer>{
    
}
