package com.clubalpha.repositories;

import org.springframework.data.repository.CrudRepository;
import com.clubalpha.models.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer>{
    
}
