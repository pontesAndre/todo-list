package br.com.andrepontes.todolist.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.andrepontes.todolist.model.Task;

public class UpdateTaskForm {
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String summary;
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String description;

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

	public Task update(Task task) {
		task.setSummary(getSummary());
		task.setDescription(getDescription());
		return task;
	}

}
