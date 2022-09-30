package com.clubalpha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.ManyToOne;

@Entity
@Table(name="member_classes")
public class MemberClass {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMC;
 
    public Integer getIdMC() {
        return idMC;
    }

    public void setIdMC(Integer idMC) {
        this.idMC = idMC;
    }
    
    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "idMember", referencedColumnName="idMember")
    private Member member;
    
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idClass", referencedColumnName="idClass")
    private Class classe;
    
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    public Class getClasse() {
        return classe;
    }

    public void setClass(Class classe) {
        this.classe = classe;
    }
}