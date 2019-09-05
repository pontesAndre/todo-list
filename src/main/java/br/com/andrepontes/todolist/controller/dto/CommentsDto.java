package br.com.andrepontes.todolist.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.andrepontes.todolist.model.Comments;

public class CommentsDto {

	private Long id;
	private String message;
	private LocalDateTime dateCreate;
	private String authorName;

	public CommentsDto(Comments comments) {
		this.id = comments.getId();
		this.message = comments.getMessage();
		this.dateCreate = comments.getDateCreate();
		this.authorName = comments.getAuthor().getName();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public static List<CommentsDto> process(List<Comments> comments) {
		return comments.stream().map(CommentsDto::new).collect(Collectors.toList());
	}

}
