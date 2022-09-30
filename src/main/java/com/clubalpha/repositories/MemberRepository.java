package com.clubalpha.repositories;

import org.springframework.data.repository.CrudRepository;
import com.clubalpha.models.Member;

public interface MemberRepository extends CrudRepository<Member, Integer>{
    
}
