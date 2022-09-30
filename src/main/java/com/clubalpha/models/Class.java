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
@Table(name="classes")
public class Class {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idClass;
    private String name;  
    private String description;
    private Integer capacity;
    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idInstructor", referencedColumnName="idInstructor")
    private Instructor instructor;
    
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}