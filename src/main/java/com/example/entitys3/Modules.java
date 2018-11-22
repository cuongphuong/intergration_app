package com.example.entitys3;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="tb_module")
public class Modules {
	
	@Id
	@Column(name="module_id", nullable=false)
	private int moduleID;
	
	@Column(name="module_name", nullable=false)
	private String moduleName;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="module")
	private List<Functions> functions;

	public Modules() {
		super();
	}

	public Modules(int moduleID, String moduleName) {
		super();
		this.moduleID = moduleID;
		this.moduleName = moduleName;
	}

	public int getModuleID() {
		return moduleID;
	}

	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
