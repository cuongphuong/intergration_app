package com.example.entitys3;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="tb_access_control")
public class AccessControl implements Serializable{
	
	@Column(name="function_id", nullable=false, insertable=false, updatable=false)
	private int functionID;
	
	@Column(name="user_id", nullable=false, insertable=false, updatable=false)
	private int userID;
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccessControlKey accessControlKey;
	
	@ManyToOne
	@MapsId("functionID")
	@JoinColumn(name = "function_id")
	private Functions function;
	
	@ManyToOne
	@MapsId("userID")
	@JoinColumn(name = "user_id")
	private Users user;
	
	@Column(name="status", nullable=false)
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}

	public int getFunctionID() {
		return functionID;
	}

	public void setFunctionID(int functionID) {
		this.functionID = functionID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public AccessControl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public AccessControlKey getAccessControlKey() {
		return accessControlKey;
	}

	public void setAccessControlKey(AccessControlKey accessControlKey) {
		this.accessControlKey = accessControlKey;
	}

	public AccessControl(AccessControlKey accessControlKey, Functions function, Users user, boolean status) {
		super();
		this.accessControlKey = accessControlKey;
		this.function = function;
		this.user = user;
		this.status = status;
	}
}
