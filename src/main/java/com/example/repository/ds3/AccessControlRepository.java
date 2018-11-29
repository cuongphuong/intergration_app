package com.example.repository.ds3;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entitys3.AccessControl;

@Repository("accessControlRepository")
public interface AccessControlRepository extends CrudRepository<AccessControl, Integer>{
	
	@Query("select d from AccessControl d where d.functionID = :function_id and d.userID = :user_id")
	public Optional<AccessControl> findByDoubleKey(@Param("function_id") int functionID, @Param("user_id") int userID);
	
	@Query("select d from AccessControl d where d.userID = :user_id")
	public Iterable<AccessControl> findAllRolesByUser(@Param("user_id") int userID);
}
