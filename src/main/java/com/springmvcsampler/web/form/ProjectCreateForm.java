package com.springmvcsampler.web.form;

import com.springmvcsampler.model.Project;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectCreateForm extends AbstractForm {

	@NotBlank(message = ProjectCreateForm.NOT_BLANK_MESSAGE)
	private String name;

	@NotBlank(message = ProjectCreateForm.NOT_BLANK_MESSAGE)
	private String clientName;

	@NotBlank(message = ProjectCreateForm.NOT_BLANK_MESSAGE)
	private Date startDate;

	@NotBlank(message = ProjectCreateForm.NOT_BLANK_MESSAGE)
	private Date endDate;

	@NotBlank(message = ProjectCreateForm.NOT_BLANK_MESSAGE)
	private String description = "";

	@NotNull
	private Double globalOHP = 0.0d;

	public Project createProject() {
        return new Project(getName(), getClientName(), getStartDate(), getEndDate(), getDescription(), getGlobalOHP());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getGlobalOHP() {
		return globalOHP;
	}

	public void setGlobalOHP(Double globalOHP) {
		this.globalOHP = globalOHP;
	}

	@Override
	public String toString() {
		return "ProjectCreateForm{" +
				"name='" + name + '\'' +
				", clientName='" + clientName + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", description='" + description + '\'' +
				", globalOHP=" + globalOHP +
				'}';
	}
}
