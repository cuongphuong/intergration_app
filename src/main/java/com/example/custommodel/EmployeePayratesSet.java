package com.example.custommodel;

import com.example.entitys1.PayRates;

public class EmployeePayratesSet {
	private PayRates payrate;
	private long employeeID;
	private long SSN;
	
	public PayRates getPayrate() {
		return payrate;
	}
	
	public void setPayrate(PayRates payrate) {
		this.payrate = payrate;
	}
	
	public long getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	
	public long getSSN() {
		return SSN;
	}
	
	public void setSSN(long sSN) {
		SSN = sSN;
	}
	
	public EmployeePayratesSet(PayRates payrate, long employeeID, long sSN) {
		super();
		this.payrate = payrate;
		this.employeeID = employeeID;
		SSN = sSN;
	}
	
	public EmployeePayratesSet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
