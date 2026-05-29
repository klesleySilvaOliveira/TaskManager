package com.dio.taskmanager.domain;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException(TaskId taskId) {
		super("Task with ID " + taskId + " not found");
	}

}
