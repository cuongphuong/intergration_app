package com.example.repository.ds1;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entitys1.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
	
	@Query("select e from Employee e")
	public List<Employee> findByPage(Pageable page);
}
