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
	
	public boolean existsById(int id) {
		return userRepository.existsById(id);
	}
	
	public Users save(Users user) {
		userRepository.save(user);
		return user;
	}
	
	public void deleteByID(int id) {
		userRepository.deleteById(id);
	}
	
	public Iterable<Users> findAll() {
		return userRepository.findAll();
	}
}
