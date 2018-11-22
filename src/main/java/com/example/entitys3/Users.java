package com.example.entitys3;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_users")
public class Users{
	@Id
	@Column(name="user_id", nullable=false)
	private int userID;
	
	@Column(name="full_name", nullable=false)
	private String fullName;
	
	@Column(name="username", nullable=false, unique=true)
	private String userName;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<AccessControl> accessControls = new ArrayList<AccessControl>();

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AccessControl> getAccessControls() {
		return accessControls;
	}

	public void setAccessControls(List<AccessControl> accessControls) {
		this.accessControls = accessControls;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int userID, String fullName, String userName, String password, List<AccessControl> accessControls) {
		super();
		this.userID = userID;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.accessControls = accessControls;
	}
}
