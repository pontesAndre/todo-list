package br.com.andrepontes.todolist.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.andrepontes.todolist.model.PriorityTask;
import br.com.andrepontes.todolist.model.StatusTask;
import br.com.andrepontes.todolist.model.Task;

public class TaskDetailDto {

	private Long id;
	private String summary;
	private String description;
	private LocalDateTime dateCreate;
	private StatusTask status;
	private String authorName;
	private List<CommentsDto> comments;
	private PriorityTask priority;

	public TaskDetailDto(Task task) {
		this.id = task.getId();
		this.summary = task.getSummary();
		this.description = task.getDescription();
		this.dateCreate = task.getDateCreate();
		this.status = task.getStatus();
		this.authorName = task.getAuthor().getName();
		this.comments = CommentsDto.process(task.getComments());
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

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public StatusTask getStatus() {
		return status;
	}

	public String getAuthorName() {
		return authorName;
	}

	public List<CommentsDto> getComments() {
		return comments;
	}

	public PriorityTask getPriority() {
		return priority;
	}

}
