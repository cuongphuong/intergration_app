package com.example.entitys2;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Employment")
public class Employment implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Employee_ID", nullable=false, updatable = false, insertable = false)
	private long employee_ID;
	
	@Column(name="Employment_Status", nullable=true)
	private String employment_Status;
	
	@Column(name="Hire_Date", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date hire_Date;
	
	@Column(name="Workers_Comp_Code", nullable=true)
	private String workers_Comp_Code;
	
	@Column(name="Termination_Date", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date termination_Date;
	
	@Column(name="Rehire_Date", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date rehire_Date;
	
	@Column(name="Last_Review_Date", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_Review_Date;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Employee_ID", insertable=false, updatable=false)
	private Personal personal;

	public long getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(long employee_ID) {
		this.employee_ID = employee_ID;
	}

	public String getEmployment_Status() {
		return employment_Status;
	}

	public void setEmployment_Status(String employment_Status) {
		this.employment_Status = employment_Status;
	}

	public Date getHire_Date() {
		return hire_Date;
	}

	public void setHire_Date(Date hire_Date) {
		this.hire_Date = hire_Date;
	}

	public String getWorkers_Comp_Code() {
		return workers_Comp_Code;
	}

	public void setWorkers_Comp_Code(String workers_Comp_Code) {
		this.workers_Comp_Code = workers_Comp_Code;
	}

	public Date getTermination_Date() {
		return termination_Date;
	}

	public void setTermination_Date(Date termination_Date) {
		this.termination_Date = termination_Date;
	}

	public Date getRehire_Date() {
		return rehire_Date;
	}

	public void setRehire_Date(Date rehire_Date) {
		this.rehire_Date = rehire_Date;
	}

	public Date getLast_Review_Date() {
		return last_Review_Date;
	}

	public void setLast_Review_Date(Date last_Review_Date) {
		this.last_Review_Date = last_Review_Date;
	}
	
	
}
