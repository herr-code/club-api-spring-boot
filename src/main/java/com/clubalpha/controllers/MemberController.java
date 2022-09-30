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
import com.clubalpha.models.Member;
import com.clubalpha.repositories.MemberRepository;

@Controller
@RequestMapping(path="/member")
public class MemberController {
    @Autowired 
    private MemberRepository memberRepository;
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewMember (@RequestParam String name,
      @RequestParam String lastname,
      @RequestParam String academicDegree,
      @RequestParam String email,
      @RequestParam String dateOfBirth,
      @RequestParam String status,
      @RequestParam String address,
      @RequestParam String phoneNumber,
      @RequestParam String profileImg) {

        Member newMember = new Member();
        newMember.setName(name);
        newMember.setLastname(lastname);
        newMember.setAcademicDegree(academicDegree);
        newMember.setEmail(email);
        newMember.setDateOfBirth(dateOfBirth);
        newMember.setStatus(Integer.parseInt(status));
        newMember.setAddress(address);
        newMember.setPhoneNumber(phoneNumber);
        newMember.setProfileImg(profileImg);

        memberRepository.save(newMember);
        return "Member Saved";
    }
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    @PutMapping(path="/changeMember/{id}")
    public @ResponseBody String changeMember(@PathVariable int id, @RequestParam String name,
      @RequestParam String lastname,
      @RequestParam String academicDegree,
      @RequestParam String email,
      @RequestParam String dateOfBirth,
      @RequestParam String status,
      @RequestParam String address,
      @RequestParam String phoneNumber,
      @RequestParam String profileImg) {
        
        Member updateMember = memberRepository.findById(id).get();     

        updateMember.setName(name);
        updateMember.setLastname(lastname);
        updateMember.setAcademicDegree(academicDegree);
        updateMember.setEmail(email);
        updateMember.setDateOfBirth(dateOfBirth);
        updateMember.setStatus(Integer.parseInt(status));
        updateMember.setAddress(address);
        updateMember.setPhoneNumber(phoneNumber);
        updateMember.setProfileImg(profileImg);

        memberRepository.save(updateMember);

        return "Member updated";
    }
    
    @DeleteMapping(path="/deleteMember/{id}")
    public @ResponseBody String deleteMember(@PathVariable int id) {
        memberRepository.deleteById(id);
        return "Member deleted";
    }
}
