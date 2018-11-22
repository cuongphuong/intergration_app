package com.example.repository.ds2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entitys2.Employment;

@Repository("employmentRepository")
public interface EmploymentRepository extends CrudRepository<Employment, Long>{
	
}
