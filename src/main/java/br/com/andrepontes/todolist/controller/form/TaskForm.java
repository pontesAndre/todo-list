package br.com.andrepontes.todolist.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.andrepontes.todolist.model.Task;
import br.com.andrepontes.todolist.repository.ProjectRepository;

public class TaskForm {
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String summary;
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String description;
	@NotNull
	@NotEmpty
	private String projectName;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Task process(ProjectRepository projectRepository) {
		return new Task(getSummary(), getDescription(), projectRepository.findByName(getProjectName()));
	}

}
