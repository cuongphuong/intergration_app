package com.example.custommodel;

import java.util.ArrayList;
import java.util.List;

import com.example.entitys3.AccessControl;
import com.example.entitys3.AccessControlKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccessControlUpdate {

	private String roles;
	private String status;
	private int userID;

	@JsonIgnore
	public List<AccessControl> getConvertToLstAccessControl() {
		// get roles
		String lstFunction[] = this.roles.split(";");
		String lstStatus[] = this.status.split(";");

		List<AccessControl> lstAccessControl = new ArrayList<AccessControl>();

		for (int i = 0; i < lstFunction.length; i++) {
			AccessControlKey key = new AccessControlKey(Integer.parseInt(lstFunction[i]), userID);
			boolean statusItem = (Integer.parseInt(lstStatus[i]) == 1) ? true : false;
			
			int functionID = Integer.parseInt(lstFunction[i]);
			
			lstAccessControl.add(new AccessControl(functionID, userID, key, statusItem));
		}
		return lstAccessControl;
	}

	public AccessControlUpdate(String roles, String status, int userID) {
		super();
		this.roles = roles;
		this.status = status;
		this.userID = userID;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
