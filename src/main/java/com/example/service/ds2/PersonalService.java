package com.example.service.ds2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	
	public Optional<Personal> findByID(long ID) {
		return personalRepository.findById(ID);
	}
	
	public Personal save(Personal personal) {
		return personalRepository.save(personal);
	}
	
	public void deleteByID(long id) {
		personalRepository.deleteById(id);
	}
	
	public boolean existsById(long id) {
		return personalRepository.existsById(id);
	}
	
	public List<Personal> findByPage(int page){
		return personalRepository.findByPage(PageRequest.of(page, 5));
	}
	
	public List<Personal> kiNiemViecLam(int day, int month){
		return personalRepository.kiNienViecLam(day, month);
	}
}
