package com.dio.taskmanager.domain;

import java.util.Optional;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {

	private TaskId id;
	private String title;
	private Optional<String> description;
	private TaskStatus status;
	
	public Task(String title, Optional<String> description) {
		super();
		Assert.notNull(title, "Title must not be NULL");
		this.id = new TaskId();
		this.title = title;
		this.description = description;
		this.status = TaskStatus.PENDING;
	}
	
	public void update(Optional<String> title, Optional<String> description, Optional<TaskStatus> status) {
		
		title.ifPresent(this::setTitle);
		description.ifPresent(d -> this.setDescription(Optional.of(d)));
		status.ifPresent(this::setStatus);
		
	}
}
