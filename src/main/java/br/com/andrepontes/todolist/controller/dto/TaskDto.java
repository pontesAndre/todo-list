package br.com.andrepontes.todolist.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.andrepontes.todolist.model.StatusTask;
import br.com.andrepontes.todolist.model.Task;

public class TaskDto {

	private Long id;
	private String summary;
	private String description;
	private StatusTask status;
	private LocalDateTime dateCreate;

	public TaskDto(Task task) {
		this.id = task.getId();
		this.summary = task.getSummary();
		this.description = task.getDescription();
		this.status = task.getStatus();
		this.dateCreate = task.getDateCreate();

	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public Long getId() {
		return id;
	}

	public String getSummary() {
		return summary;
	}

	public String getDescription() {
		return description;
	}

	public StatusTask getStatus() {
		return status;
	}

	public static Page<TaskDto> process(Page<Task> tasks) {
		return tasks.map(TaskDto::new);
	}

}
