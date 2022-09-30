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
import com.clubalpha.models.MemberClass;
import com.clubalpha.repositories.MemberClassRepository;
import com.clubalpha.models.Member;
import com.clubalpha.models.Class;
import com.clubalpha.repositories.MemberRepository;
import com.clubalpha.repositories.ClassRepository;

@Controller
@RequestMapping(path="/memberclass")
public class MemberClassController {
    @Autowired 
    private MemberRepository memberRepository;
    @Autowired 
    private ClassRepository classRepository; 
    @Autowired 
    private MemberClassRepository memberClassRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewMemberClass (@RequestParam String idMember,
      @RequestParam String idClass) {

        MemberClass newMemberClass = new MemberClass();
        Member member = memberRepository.findById(Integer.parseInt(idMember)).get();
        Class classe = classRepository.findById(Integer.parseInt(idClass)).get();
     
        newMemberClass.setMember(member);
        newMemberClass.setClass(classe);

        memberClassRepository.save(newMemberClass);
        return "MemberClass Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<MemberClass> getAllMemberClasses() {
        return memberClassRepository.findAll();
    }
    
    @DeleteMapping(path="/deleteMemberClass/{id}")
    public @ResponseBody String deleteMemberClass(@PathVariable int id) {
        memberClassRepository.deleteById(id);
        return "MemberClass deleted";
    }
}
