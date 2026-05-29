package com.dio.taskmanager.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.taskmanager.application.input.UpdateTaskInput;
import com.dio.taskmanager.application.output.TaskOutput;
import com.dio.taskmanager.domain.TaskId;
import com.dio.taskmanager.domain.TaskNotFoundException;
import com.dio.taskmanager.domain.TaskRepository;

@Service
public class UpdateTaskUseCase {

	@Autowired
	private TaskRepository repository;
	
	public TaskOutput execute(TaskId taskId, UpdateTaskInput input) {
		var task = repository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
		task.update(input.title(), input.description(), input.status());
		return TaskOutput.from(repository.save(task));
	}
}
