package com.example.repository.ds2;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entitys2.Personal;

@Repository("personalRepository")
public interface PersonalRepository extends CrudRepository<Personal, Long>{
	
	@Query("select c from Personal c where c.employee_ID >= :firstID and c.employee_ID <= :lastID")
	public List<Personal> getPersonalBySegmentID(@Param("firstID") long firstID, @Param("lastID") long lastID);

	@Query("select e from Personal e")
	public List<Personal> findByPage(Pageable page);
	
	@Query("select c from Personal c join c.employment e where day(e.hire_Date) >= :day and month(e.hire_Date) = :month")
	public List<Personal> kiNienViecLam(@Param("day") int day, @Param("month") int month);

}
