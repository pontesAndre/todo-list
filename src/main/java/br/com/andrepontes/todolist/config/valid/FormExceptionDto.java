package br.com.andrepontes.todolist.config.valid;

public class FormExceptionDto {

	private String field;
	private String error;

	public FormExceptionDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}

}
