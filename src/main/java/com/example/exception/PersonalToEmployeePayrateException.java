package com.example.exception;

public class PersonalToEmployeePayrateException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersonalToEmployeePayrateException(String message) {
		super("Lổi khi thêm Paytate và Convert Personal to Employee" + message);
		// TODO Auto-generated constructor stub
	}
}
