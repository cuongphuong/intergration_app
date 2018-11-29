package com.example.service.ds3;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys3.Functions;
import com.example.repository.ds3.FunctionRepository;

@Service("functionService")
public class FunctionService {
	@Autowired
	FunctionRepository functionRepository;
	
	public Optional<Functions> findByID(int id) {
		return functionRepository.findById(id);
	}
	
	public Iterable<Functions> findAll() {
		return functionRepository.findAll();
	}
}
