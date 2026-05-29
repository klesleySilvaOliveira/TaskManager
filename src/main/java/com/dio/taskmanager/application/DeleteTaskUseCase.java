package com.dio.taskmanager.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.taskmanager.domain.TaskId;
import com.dio.taskmanager.domain.TaskNotFoundException;
import com.dio.taskmanager.domain.TaskRepository;

@Service
public class DeleteTaskUseCase {

	@Autowired
	private TaskRepository repository;
	
	public void execute(TaskId taskId) {
		
		if (repository.findById(taskId).isEmpty()) {
			throw new TaskNotFoundException(taskId);
		}
		
		repository.delete(taskId);
		
	}
}
