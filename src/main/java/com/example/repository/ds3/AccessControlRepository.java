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
	
	@Query("update AccessControl a set a.status = :status where a.functionID = :function_id and a.userID = :user_id")
	public void updateStatusByKey(@Param("status") boolean status, @Param("function_id") int functionID, @Param("user_id") int userID);
	
	@Query("select count(d) from AccessControl d where d.functionID = :function_id and d.userID = :user_id")
	public int checkByDoubleKey(@Param("function_id") int functionID, @Param("user_id") int userID);
	
	@Query("delete from AccessControl d where d.functionID = :function_id and d.userID = :user_id")
	public void deleteByDoubleKey(@Param("function_id") int functionID, @Param("user_id") int userID);
}
