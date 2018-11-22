package com.example.service.ds2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys2.Employment;
import com.example.repository.ds2.EmploymentRepository;


@Service("employmentService")
public class EmploymentService {
	
	@Autowired
	private EmploymentRepository employmentRepository;
	
	public Iterable<Employment> findAll(){
		return employmentRepository.findAll();
	}
}
