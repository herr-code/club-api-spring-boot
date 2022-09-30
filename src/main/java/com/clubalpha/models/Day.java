package com.clubalpha.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="days")
public class Day {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDay;
    private String name;    
   

    public Integer getIdDay() {
        return idDay;
    }

    public void setIdDay(Integer idDay) {
        this.idDay = idDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
