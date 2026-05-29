package com.dio.taskmanager.application.input;

import java.util.Optional;

import com.dio.taskmanager.domain.TaskStatus;

public record UpdateTaskInput(Optional<String> title, 
		Optional<String> description,
		Optional<TaskStatus> status) {

}
