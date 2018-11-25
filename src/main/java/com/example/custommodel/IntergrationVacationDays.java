package com.example.custommodel;

public class IntergrationVacationDays {
	private long employeeID;
	private String fistName;
	private String lastName;
	private Boolean gender; //giới tính
	private String ethnicity;
	private String address1;
	private String address2;
	private String phoneNumber;
	private int vacationDays;
	public long getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(long employeeID) {
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
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getVacationDays() {
		return vacationDays;
	}
	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}
	public IntergrationVacationDays(long employeeID, String fistName, String lastName, Boolean gender, String ethnicity,
			String address1, String address2, String phoneNumber) {
		super();
		this.employeeID = employeeID;
		this.fistName = fistName;
		this.lastName = lastName;
		this.gender = gender;
		this.ethnicity = ethnicity;
		this.address1 = address1;
		this.address2 = address2;
		this.phoneNumber = phoneNumber;
	}
}