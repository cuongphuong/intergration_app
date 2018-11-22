package com.example.repository.ds2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entitys2.Personal;

@Repository("personalRepository")
public interface PersonalRepository extends CrudRepository<Personal, Long>{

}
