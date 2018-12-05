package com.example.custommodel;

public class AccessControlResUpdate {
	public int functionID;
	public String functionName;
	public int userID;
	public boolean status;

	public int getFunctionID() {
		return functionID;
	}

	public void setFunctionID(int functionID) {
		this.functionID = functionID;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public int getUserID() {
		return userID;
	}

	public AccessControlResUpdate(int functionID, String functionName, int userID, boolean status) {
		super();
		this.functionID = functionID;
		this.functionName = functionName;
		this.userID = userID;
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public AccessControlResUpdate(int functionID, String functionName, int userID) {
		super();
		this.functionID = functionID;
		this.functionName = functionName;
		this.userID = userID;
	}
}
