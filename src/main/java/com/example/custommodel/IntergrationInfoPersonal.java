package com.example.custommodel;

public class IntergrationInfoPersonal {
	private long employee_ID;

	private String first_Name;

	private String last_Name;

	private String middle_Initial;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private long zip;

	private String email;

	private String phone_Number;

	private String social_Security_Number;

	private String drivers_License;

	private String marital_Status;

	private Boolean gender;

	private Boolean shareholder_Status;

	private String ethnicity;
	
	private boolean isExist;

	private boolean isSame;
	
	

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

	public long getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(long employee_ID) {
		this.employee_ID = employee_ID;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getMiddle_Initial() {
		return middle_Initial;
	}

	public void setMiddle_Initial(String middle_Initial) {
		this.middle_Initial = middle_Initial;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}

	public String getSocial_Security_Number() {
		return social_Security_Number;
	}

	public void setSocial_Security_Number(String social_Security_Number) {
		this.social_Security_Number = social_Security_Number;
	}

	public String getDrivers_License() {
		return drivers_License;
	}

	public void setDrivers_License(String drivers_License) {
		this.drivers_License = drivers_License;
	}

	public String getMarital_Status() {
		return marital_Status;
	}

	public void setMarital_Status(String marital_Status) {
		this.marital_Status = marital_Status;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Boolean getShareholder_Status() {
		return shareholder_Status;
	}

	public void setShareholder_Status(Boolean shareholder_Status) {
		this.shareholder_Status = shareholder_Status;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public boolean isSame() {
		return isSame;
	}

	public void setSame(boolean isSame) {
		this.isSame = isSame;
	}

	public IntergrationInfoPersonal(long employee_ID, String first_Name, String last_Name, String middle_Initial,
			String address1, String address2, String city, String state, long zip, String email, String phone_Number,
			String social_Security_Number, String drivers_License, String marital_Status, Boolean gender,
			Boolean shareholder_Status, String ethnicity, boolean isSame, boolean isexist) {
		super();
		this.employee_ID = employee_ID;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.middle_Initial = middle_Initial;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.phone_Number = phone_Number;
		this.social_Security_Number = social_Security_Number;
		this.drivers_License = drivers_License;
		this.marital_Status = marital_Status;
		this.gender = gender;
		this.shareholder_Status = shareholder_Status;
		this.ethnicity = ethnicity;
		this.isSame = isSame;
		this.isExist = isexist;
	}

}
