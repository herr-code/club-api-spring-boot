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
import com.clubalpha.models.Schedule;
import com.clubalpha.models.Day;
import com.clubalpha.models.Class;
import com.clubalpha.repositories.DayRepository;
import com.clubalpha.repositories.ClassRepository;
import com.clubalpha.repositories.ScheduleRepository;

@Controller
@RequestMapping(path="/schedule")
public class ScheduleController {
    @Autowired 
    private DayRepository dayRepository;
    @Autowired 
    private ClassRepository classRepository;
    @Autowired 
    private ScheduleRepository scheduleRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewSchedule (@RequestParam String idDay,
      @RequestParam String idClasse,
      @RequestParam String startTime,
      @RequestParam String endTime) {

        Schedule newSchedule = new Schedule();
        Day day = dayRepository.findById(Integer.parseInt(idDay)).get();
        Class classe = classRepository.findById(Integer.parseInt(idClasse)).get();
        
        newSchedule.setStartTime(startTime);
        newSchedule.setEndTime(endTime);
        newSchedule.setDay(day);
        newSchedule.setClasse(classe);
        scheduleRepository.save(newSchedule);
        return "Schedule Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
    
    @PutMapping(path="/changeSchedule/{id}")
    public @ResponseBody String changeSchedule(@PathVariable int id, @RequestParam String idDay,
      @RequestParam String idClasse,
      @RequestParam String startTime,
      @RequestParam String endTime) {
        
        Schedule updateSchedule = scheduleRepository.findById(id).get();     
        Day day = dayRepository.findById(Integer.parseInt(idDay)).get();
        Class classe = classRepository.findById(Integer.parseInt(idClasse)).get();
        
        updateSchedule.setDay(day);
        updateSchedule.setClasse(classe);
        updateSchedule.setStartTime(startTime);
        updateSchedule.setEndTime(endTime);

        scheduleRepository.save(updateSchedule);

        return "Schedule updated";
    }
    
    @DeleteMapping(path="/deleteSchedule/{id}")
    public @ResponseBody String deleteSchedule(@PathVariable int id) {
        scheduleRepository.deleteById(id);
        return "Schedule deleted";
    }
}
