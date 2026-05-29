package com.dio.taskmanager.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.taskmanager.application.output.TaskOutput;
import com.dio.taskmanager.domain.TaskId;
import com.dio.taskmanager.domain.TaskNotFoundException;
import com.dio.taskmanager.domain.TaskRepository;

@Service
public class GetTaskByIdUseCase {

	@Autowired
	private TaskRepository repository;
	
	public TaskOutput execute(TaskId id) {
		return repository.findById(id).map(TaskOutput::from).orElseThrow(() -> new TaskNotFoundException(id));
	}
}
