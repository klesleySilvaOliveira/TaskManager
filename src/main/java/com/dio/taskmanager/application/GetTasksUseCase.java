package com.dio.taskmanager.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.taskmanager.application.output.TaskOutput;
import com.dio.taskmanager.domain.TaskRepository;

@Service
public class GetTasksUseCase {

	@Autowired
	private TaskRepository repository;
	
	public List<TaskOutput> execute() {
		return repository.findAll().stream()
				.map(TaskOutput::from).toList();
	}
}
