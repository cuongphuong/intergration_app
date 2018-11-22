package com.example.service.ds2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys2.Personal;
import com.example.repository.ds2.PersonalRepository;

@Service("personalService")
public class PersonalService{

	@Autowired
	private PersonalRepository personalRepository;
	
	public Iterable<Personal> findAll(){
		return personalRepository.findAll();
	}
}
