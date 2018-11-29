package com.example.service.ds2;

import java.util.List;

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
	
	public List<Personal> getPersonalBySegmentID(long firstID, long lastID){
		return personalRepository.getPersonalBySegmentID(firstID, lastID);
	}
	
	public Personal save(Personal personal) {
		return personalRepository.save(personal);
	}
}
