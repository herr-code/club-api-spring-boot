package com.clubalpha.models;

import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idHorario;
    private String startTime;  
    private String endTime;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idDay", referencedColumnName="idDay")
    private Day day;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idClass", referencedColumnName="idClass")
    private Class classe;
    
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
    
    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }
    
    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
