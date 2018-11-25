package com.example.service.ds1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.entitys1.Employee;
import com.example.repository.ds1.EmployeeRepository;

@Service("employeeService")
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Iterable<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public List<Employee> findByPage(int page){
		return employeeRepository.findByPage(PageRequest.of(page, 5));
	}
}
