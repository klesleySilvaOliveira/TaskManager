package com.dio.taskmanager.infrastructure.http.request;

import java.util.Optional;

import com.dio.taskmanager.application.input.UpdateTaskInput;
import com.dio.taskmanager.domain.TaskStatus;

public record UpdateTaskRequest(Optional<String> title, Optional<String> description, Optional<String> status) {

	public UpdateTaskInput toInput() {
		return new UpdateTaskInput(title, description, status.map(TaskStatus::valueOf));
	}
}
