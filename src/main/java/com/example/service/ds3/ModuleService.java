package com.example.service.ds3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys3.Modules;
import com.example.repository.ds3.ModuleRepository;

@Service("moduleService")
public class ModuleService {
	@Autowired private ModuleRepository moduleRepository;
	
	public Iterable<Modules> findAll() {
		return moduleRepository.findAll();
	}
}
