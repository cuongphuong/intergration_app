package com.example.repository.ds3;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entitys3.Functions;

@Repository("functionRepository")
public interface FunctionRepository extends CrudRepository<Functions, Integer> {
	
	@Query("select f from Functions f JOIN f.accessControls a where a.status = true and a.userID = :userID and f.isshow = true")
	public List<Functions> findFunctionTrue(@Param("userID") int userID);
	
	
}
