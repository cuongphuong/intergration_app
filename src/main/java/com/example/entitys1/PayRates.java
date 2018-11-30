package com.example.entitys1;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="pay_rates")
public class PayRates {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPay_Rates", nullable=false)
	private int idPay_Rates;
	
	@Column(name="Pay_Rate_Name", nullable=false)
	private String pay_Rate_Name;
	
	@Column(name="Value", nullable=false)
	private BigDecimal value;
	
	@Column(name="Tax_Percentage", nullable=false)
	private BigDecimal tax_Percentage;
	
	@Column(name="Pay_Type", nullable=false)
	private int pay_Type;
	
	@Column(name="Pay_Amount", nullable=false)
	private BigDecimal pay_Amount;
	
	@Column(name="PT_Level_C", nullable=false)
	private BigDecimal pT_Level_C;
	
	@OneToMany(mappedBy="payRate")
	private List<Employee> employees;

	public int getIdPay_Rates() {
		return idPay_Rates;
	}

	public void setIdPay_Rates(int idPay_Rates) {
		this.idPay_Rates = idPay_Rates;
	}

	public String getPay_Rate_Name() {
		return pay_Rate_Name;
	}

	public void setPay_Rate_Name(String pay_Rate_Name) {
		this.pay_Rate_Name = pay_Rate_Name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getTax_Percentage() {
		return tax_Percentage;
	}

	public void setTax_Percentage(BigDecimal tax_Percentage) {
		this.tax_Percentage = tax_Percentage;
	}

	public int getPay_Type() {
		return pay_Type;
	}

	public void setPay_Type(int pay_Type) {
		this.pay_Type = pay_Type;
	}

	public BigDecimal getPay_Amount() {
		return pay_Amount;
	}

	public void setPay_Amount(BigDecimal pay_Amount) {
		this.pay_Amount = pay_Amount;
	}

	public BigDecimal getpT_Level_C() {
		return pT_Level_C;
	}

	public void setpT_Level_C(BigDecimal pT_Level_C) {
		this.pT_Level_C = pT_Level_C;
	}
}
