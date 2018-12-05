package com.example.repository.ds1;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entitys1.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
	
	@Query("select e from Employee e")
	public List<Employee> findByPage(Pageable page);
	
	@Query("select c from Employee c where c.employee_Number >= :firstID and c.employee_Number <= :lastID")
	public List<Employee> getEmployeeBySegmentID(@Param("firstID") int firstID, @Param("lastID") int lastID);
	
	@Query("select c from Employee c where c.vacation_Days >= :day")
	public List<Employee> getNgayNghi(@Param("day") int day);
	
	@Query("select c from Employee c where c.employee_Number = :id")
	public Employee getByUsernamID(@Param("id") int id);
	
	@Query("select count(c) from Employee c where c.employee_Number = :id")
	public int checkExist(@Param("id") int id);
}
