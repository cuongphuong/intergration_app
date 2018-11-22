package com.example.entitys3;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="tb_function")
public class Functions {
	
	@Id
	@Column(name="function_id", nullable=false)
	private int functionID;
	
	@Column(name="function_name", nullable=false)
	private String functionName;
	
	@Column(name="module_id", nullable=false)
	private int moduleId;

	@JoinColumn(name="module_id", updatable=false, insertable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private Modules module;
	
	@OneToMany(mappedBy="function")
	private List<AccessControl> accessControls;

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

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public Modules getModule() {
		return module;
	}

	public void setModule(Modules module) {
		this.module = module;
	}

	public List<AccessControl> getAccessControls() {
		return accessControls;
	}

	public void setAccessControls(List<AccessControl> accessControls) {
		this.accessControls = accessControls;
	}

	public Functions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Functions(int functionID, String functionName, int moduleId, Modules module,
			List<AccessControl> accessControls) {
		super();
		this.functionID = functionID;
		this.functionName = functionName;
		this.moduleId = moduleId;
		this.module = module;
		this.accessControls = accessControls;
	}
}
