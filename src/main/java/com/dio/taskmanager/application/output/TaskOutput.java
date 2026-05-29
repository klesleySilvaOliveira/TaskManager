package com.dio.taskmanager.application.output;

import java.util.Optional;

import com.dio.taskmanager.domain.Task;

public record TaskOutput(String id, String title, Optional<String> description, String status) {
	
	public static TaskOutput from(Task task) {
		return new TaskOutput(task.getId().id().toString(),
				task.getTitle(),
				task.getDescription(),
				task.getStatus().toString());
	}

}
