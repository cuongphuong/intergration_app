package com.example.entitys2;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Personal")
public class Personal implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Employee_ID")
	private long employee_ID;
	
	@Column(name="First_Name", nullable=true)
	private String first_Name;
	
	@Column(name="Last_Name", nullable=true)
	private String last_Name;
	
	@Column(name="Middle_Initial", nullable=true)
	private String middle_Initial;
	
	@Column(name="Address1", nullable=true)
	private String address1;
	
	@Column(name="Address2", nullable=true)
	private String address2;
	
	@Column(name="City", nullable=true)
	private String city;
	
	@Column(name="State", nullable=true)
	private String state;
	
	@Column(name="Zip", nullable=true)
	private long zip;
	
	@Column(name="Email", nullable=true)
	private String email;
	
	@Column(name="Phone_Number", nullable=true)
	private String phone_Number;
	
	@Column(name="Social_Security_Number", nullable=true)
	private String social_Security_Number;
	
	@Column(name="Drivers_License", nullable=true)
	private String drivers_License;
	
	@Column(name="Marital_Status", nullable=true)
	private String marital_Status;
	
	@Column(name="Gender", nullable=true)
	private Boolean gender;
	
	@Column(name="Shareholder_Status", nullable=false)
	private Boolean shareholder_Status;
	
	@Column(name="Ethnicity", nullable=true)
	private String ethnicity;
	
	//end set relationship

	@OneToOne(mappedBy="personal")
//	@JsonIgnore
	private Employment employment;
	
	@ManyToOne
	@JoinColumn(name="Benefit_Plans", nullable=true, insertable=false, updatable=false)
//	@JsonIgnore
	private BenefitPlan benefitPlan;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="personal")
	@JsonIgnore
	private List<JobHistory> jobHistorys;

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}

	public BenefitPlan getBenefitPlan() {
		return benefitPlan;
	}

	public void setBenefitPlan(BenefitPlan benefitPlan) {
		this.benefitPlan = benefitPlan;
	}
	
	public List<JobHistory> getJobHistorys() {
		return jobHistorys;
	}

	public void setJobHistorys(List<JobHistory> jobHistorys) {
		this.jobHistorys = jobHistorys;
	}
	
	
	//end set relationship

	public long getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(long employee_ID) {
		this.employee_ID = employee_ID;
	}

	public Personal() {
		super();
		// TODO Auto-generated constructor stub
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
}
