package com.example.entitys1;

import java.io.Serializable;

import javax.persistence.*;
@Embeddable
public class EmployeePK implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name="Employee_Number")
	private int employee_Number;
	
	@Column(name="Pay_Rates_idPay_Rates")
	private int pay_Rates_idPay_Rates;

	public int getEmployee_Number() {
		return employee_Number;
	}

	public void setEmployee_Number(int employee_Number) {
		this.employee_Number = employee_Number;
	}

	public int getPay_Rates_idPay_Rates() {
		return pay_Rates_idPay_Rates;
	}

	public void setPay_Rates_idPay_Rates(int pay_Rates_idPay_Rates) {
		this.pay_Rates_idPay_Rates = pay_Rates_idPay_Rates;
	}

	public EmployeePK(int employee_Number, int pay_Rates_idPay_Rates) {
		super();
		this.employee_Number = employee_Number;
		this.pay_Rates_idPay_Rates = pay_Rates_idPay_Rates;
	}

	public EmployeePK() {
		super();
		// TODO Auto-generated constructor stub
	}
}
