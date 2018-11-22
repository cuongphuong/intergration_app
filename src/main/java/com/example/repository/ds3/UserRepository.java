package com.example.repository.ds3;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entitys3.Users;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<Users, Integer>{
	
	@Query("select n from Users n where n.userName = :username")
	public Optional<Users> findByUsername(@Param("username") String username);
}
