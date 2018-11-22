package com.example.entitys2;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Job_History")
public class JobHistory {
	
	@Id
	@Column(name="ID", nullable=false)
	private long ID;
	
	@Column(name="Department", nullable=false)
	private String department;
	
	@Column(name="Division", nullable=false)
	private String division;
	
	@Column(name="Start_Date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date start_Date;
	
	@Column(name="End_Date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date end_Date;
	
	@Column(name="Job_Title", nullable=false)
	private String job_Title;
	
	@Column(name="Supervisor", nullable=false)
	private long supervisor;
	
	@Column(name="Job_Category", nullable=false)
	private String job_Category;
	
	@Column(name="Location", nullable=false)
	private String location;
	
	@Column(name="Departmen_Code", nullable=false)
	private long departmen_Code;
	
	@Column(name="Salary_Type", nullable=false)
	private long salary_Type;
	
	@Column(name="Pay_Period", nullable=false)
	private String pay_Period;
	
	@Column(name="Hours_per_Week", nullable=false)
	private long hours_per_Week;
	
	@Column(name="Hazardous_Training", nullable=false)
	private Boolean hazardous_Training;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Employee_ID", nullable=false, insertable=false, updatable=false)
	private Personal personal;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public String getJob_Title() {
		return job_Title;
	}

	public void setJob_Title(String job_Title) {
		this.job_Title = job_Title;
	}

	public long getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(long supervisor) {
		this.supervisor = supervisor;
	}

	public String getJob_Category() {
		return job_Category;
	}

	public void setJob_Category(String job_Category) {
		this.job_Category = job_Category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getDepartmen_Code() {
		return departmen_Code;
	}

	public void setDepartmen_Code(long departmen_Code) {
		this.departmen_Code = departmen_Code;
	}

	public long getSalary_Type() {
		return salary_Type;
	}

	public void setSalary_Type(long salary_Type) {
		this.salary_Type = salary_Type;
	}

	public String getPay_Period() {
		return pay_Period;
	}

	public void setPay_Period(String pay_Period) {
		this.pay_Period = pay_Period;
	}

	public long getHours_per_Week() {
		return hours_per_Week;
	}

	public void setHours_per_Week(long hours_per_Week) {
		this.hours_per_Week = hours_per_Week;
	}

	public Boolean getHazardous_Training() {
		return hazardous_Training;
	}

	public void setHazardous_Training(Boolean hazardous_Training) {
		this.hazardous_Training = hazardous_Training;
	}
}
