package com.example.entitys2;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Benefit_Plans")
public class BenefitPlan {
	@Id
	@Column(name="Benefit_Plan_ID", nullable=false)
	private long benefit_Plan_ID;
	
	@Column(name="Plan_Name", nullable=true)
	private String plan_Name;
	
	@Column(name="Deductable", nullable=true)
	private long deductable;
	
	@Column(name="Percentage_CoPay", nullable=true)
	private int percentage_CoPay;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="benefitPlan")
	private List<Personal> personals;

	public BenefitPlan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getBenefit_Plan_ID() {
		return benefit_Plan_ID;
	}

	public void setBenefit_Plan_ID(long benefit_Plan_ID) {
		this.benefit_Plan_ID = benefit_Plan_ID;
	}

	public String getPlan_Name() {
		return plan_Name;
	}

	public void setPlan_Name(String plan_Name) {
		this.plan_Name = plan_Name;
	}

	public long getDeductable() {
		return deductable;
	}

	public void setDeductable(long deductable) {
		this.deductable = deductable;
	}

	public int getPercentage_CoPay() {
		return percentage_CoPay;
	}

	public void setPercentage_CoPay(int percentage_CoPay) {
		this.percentage_CoPay = percentage_CoPay;
	}
	
}
