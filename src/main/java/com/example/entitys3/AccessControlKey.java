package com.example.entitys3;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class AccessControlKey implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "function_id", nullable=false)
	private int functionID;
	
	@Column(name = "user_id", nullable=false)
	private int userID;

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

	public AccessControlKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessControlKey(int functionID, int userID) {
		super();
		this.functionID = functionID;
		this.userID = userID;
	}
}
