package com.dio.taskmanager.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.taskmanager.application.input.CreateTaskInput;
import com.dio.taskmanager.application.output.TaskOutput;
import com.dio.taskmanager.domain.Task;
import com.dio.taskmanager.domain.TaskRepository;

@Service
public class CreateTaskUseCase {

	@Autowired
	private TaskRepository repository;
	
	public TaskOutput execute(CreateTaskInput input) {
		var task = new Task(input.title(), input.description());
		var saved = repository.save(task);
		return TaskOutput.from(saved);
	}
}
