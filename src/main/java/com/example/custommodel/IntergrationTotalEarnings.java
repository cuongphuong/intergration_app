package com.example.custommodel;

public class IntergrationTotalEarnings {
	//Thông tin tích hợp tổng thu nhập nhân viên
	private int employeeID;
	private String fistName;
	private String lastName;
	private Boolean gender; //giới tính
	private String ethnicity;
	private Double totalEarnings;
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getFistName() {
		return fistName;
	}
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public Double getTotalEarnings() {
		return totalEarnings;
	}
	public void setTotalEarnings(Double totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	public IntergrationTotalEarnings() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IntergrationTotalEarnings(int employeeID, String fistName, String lastName, Boolean gender, String ethnicity,
			Double totalEarnings) {
		super();
		this.employeeID = employeeID;
		this.fistName = fistName;
		this.lastName = lastName;
		this.gender = gender;
		this.ethnicity = ethnicity;
		this.totalEarnings = totalEarnings;
	}
	
	
}
