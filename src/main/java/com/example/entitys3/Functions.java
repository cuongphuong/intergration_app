package com.example.entitys3;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.text.StringEscapeUtils;

@Entity
@Table(name = "tb_function")
public class Functions {

	@Id
	@Column(name = "function_id", nullable = false)
	private int functionID;

	@Column(name = "function_name", nullable = false)
	private String functionName;

	@Column(name = "module_id", nullable = false)
	private int moduleId;

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "isshow", nullable = false)
	private boolean isshow;

	@JsonIgnore
	@JoinColumn(name = "module_id", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Modules module;

	@JsonIgnore
	@OneToMany(mappedBy = "function")
	private List<AccessControl> accessControls;

	public int getFunctionID() {
		return functionID;
	}

	public boolean isIsshow() {
		return isshow;
	}

	public void setIsshow(boolean isshow) {
		this.isshow = isshow;
	}

	public String getImage() {
		return StringEscapeUtils.unescapeHtml4(image);
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
