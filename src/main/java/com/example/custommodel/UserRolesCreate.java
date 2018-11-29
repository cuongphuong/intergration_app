package com.example.custommodel;

import com.example.entitys3.Users;

public class UserRolesCreate {
	private Users user;
	private String roles;
	
	public UserRolesCreate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserRolesCreate(Users user, String roles) {
		super();
		this.user = user;
		this.roles = roles;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public String getRoles() {
		return roles;
	}
	
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
}
