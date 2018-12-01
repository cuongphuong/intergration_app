package com.example.custommodel;

public class RoleCustom {
	private int moduleID;
	private int functionID;
	public int getModuleID() {
		return moduleID;
	}
	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}
	public int getFunctionID() {
		return functionID;
	}
	public void setFunctionID(int functionID) {
		this.functionID = functionID;
	}
	public RoleCustom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleCustom(int moduleID, int functionID) {
		super();
		this.moduleID = moduleID;
		this.functionID = functionID;
	}
	
	
}