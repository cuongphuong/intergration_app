package com.example.exception;

public class AddUserAndRolesException extends Exception{
	private static final long serialVersionUID = 1L;

	public AddUserAndRolesException(String message) {
		super("Lổi khi thêm User và Roles" + message);
		// TODO Auto-generated constructor stub
	}
	
}
