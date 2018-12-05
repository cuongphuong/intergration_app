package com.example.service.ds1;

import java.util.List;
import java.util.Optional;

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
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Optional<Employee> findByID(int id) {
		return employeeRepository.findById(id);
	}
	
	public boolean existsById(int id) {
		return employeeRepository.existsById(id);
	}
	
	public void deleteByID(int id) {
		employeeRepository.deleteById(id);
	}
	
	public List<Employee> getPersonalBySegmentID(int firstID, int lastID){
		return employeeRepository.getEmployeeBySegmentID(firstID, lastID);
	}
	
	public List<Employee> getNgayNghiQuaHan(int day){
		return employeeRepository.getNgayNghi(day);
	}
	
	public Employee getByUserID(int id) {
		return employeeRepository.getByUsernamID(id);
	}
	
	public boolean checkExist(int id) {
		return employeeRepository.checkExist(id) > 0;
	}
}
