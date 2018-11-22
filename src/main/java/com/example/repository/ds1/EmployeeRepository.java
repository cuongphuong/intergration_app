package com.example.repository.ds1;

import org.springframework.data.repository.CrudRepository;

import com.example.entitys1.Employee;

public interface EmployeeRepository  extends CrudRepository<Employee, Integer>{

}
