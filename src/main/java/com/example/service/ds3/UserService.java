package com.example.service.ds3;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entitys3.Users;
import com.example.repository.ds3.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public Optional<Users> findByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	public Optional<Users> findByID(int id) {
		return userRepository.findById(id);
	}
}
