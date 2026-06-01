package com.dio.taskmanager.infrastructure.http.request;

import java.util.Optional;

import com.dio.taskmanager.application.input.CreateTaskInput;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
		@NotBlank
		@Size(min = 3, max = 100)
		String title, 
		Optional<@Size(max = 500) String> description) {

	public CreateTaskInput toInput() {
		return new CreateTaskInput(title, description);
	}
}
