package com.example.entitys1;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="employee")
@IdClass(EmployeePK.class)
public class Employee {
	
	@Id
	@Column(name="Employee_Number", nullable=false)
	private int employee_Number;
	
	@Id
	@Column(name="Pay_Rates_idPay_Rates")
	private int pay_Rates_idPay_Rates;
	
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="employee_Number", column = @Column(name="idEmployee")),
		@AttributeOverride(name="pay_Rates_idPay_Rates", column = @Column(name="Pay_Rates_idPay_Rates"))
	})
	private EmployeePK pk;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Pay_Rates_idPay_Rates", insertable=false, updatable=false)
	private PayRates payRate;
	
	
	public PayRates getPayRate() {
		return payRate;
	}

	public void setPayRate(PayRates payRate) {
		this.payRate = payRate;
	}

	public EmployeePK getPk() {
		return pk;
	}

	public void setPk(EmployeePK pk) {
		this.pk = pk;
	}

	@Column(name="Last_Name", nullable=false)
	private String last_Name;
	
	@Column(name="First_Name", nullable=false)
	private String first_Name;
	
	@Column(name="SSN", nullable=false)
	private long SSN;
	
	@Column(name="Pay_Rate", nullable=false)
	private String pay_Rate;
	
	
	@Column(name="Vacation_Days", nullable=false)
	private int vacation_Days;
	
	@Column(name="Paid_To_Date", nullable=false)
	private BigDecimal paid_To_Date;
	
	@Column(name="Paid_Last_Year", nullable=false)
	private BigDecimal paid_Last_Year;

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

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public long getSSN() {
		return SSN;
	}

	public void setSSN(long sSN) {
		SSN = sSN;
	}

	public String getPay_Rate() {
		return pay_Rate;
	}

	public void setPay_Rate(String pay_Rate) {
		this.pay_Rate = pay_Rate;
	}

	public int getVacation_Days() {
		return vacation_Days;
	}

	public void setVacation_Days(int vacation_Days) {
		this.vacation_Days = vacation_Days;
	}

	public BigDecimal getPaid_To_Date() {
		return paid_To_Date;
	}

	public void setPaid_To_Date(BigDecimal paid_To_Date) {
		this.paid_To_Date = paid_To_Date;
	}

	public BigDecimal getPaid_Last_Year() {
		return paid_Last_Year;
	}

	public void setPaid_Last_Year(BigDecimal paid_Last_Year) {
		this.paid_Last_Year = paid_Last_Year;
	}
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
}
