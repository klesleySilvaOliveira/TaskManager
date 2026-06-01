package com.dio.taskmanager.infrastructure.http;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dio.taskmanager.application.CreateTaskUseCase;
import com.dio.taskmanager.application.DeleteTaskUseCase;
import com.dio.taskmanager.application.GetTaskByIdUseCase;
import com.dio.taskmanager.application.GetTasksUseCase;
import com.dio.taskmanager.application.UpdateTaskUseCase;
import com.dio.taskmanager.domain.TaskId;
import com.dio.taskmanager.infrastructure.http.request.CreateTaskRequest;
import com.dio.taskmanager.infrastructure.http.request.UpdateTaskRequest;
import com.dio.taskmanager.infrastructure.http.response.TaskResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
	
	@Autowired
	private CreateTaskUseCase createTaskUseCase;
	
	@Autowired
	private GetTasksUseCase getTasksUseCase;
	
	@Autowired
	private GetTaskByIdUseCase getTaskByIdUseCase;
	
	@Autowired
	private DeleteTaskUseCase deleteTaskUseCase;
	
	@Autowired
	private UpdateTaskUseCase updateTaskUseCase;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	TaskResponse create(@RequestBody @Valid CreateTaskRequest request) {
		var input = request.toInput();
		var output = createTaskUseCase.execute(input);
		return TaskResponse.from(output);
	}
	
	@GetMapping
	List<TaskResponse> list() {
		return getTasksUseCase.execute().stream().map(TaskResponse::from).toList();
	}

	@GetMapping(value = "/{id}")
	TaskResponse read(@PathVariable UUID id) {
		var output = getTaskByIdUseCase.execute(new TaskId(id));
		return TaskResponse.from(output);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable UUID id) {
		deleteTaskUseCase.execute(new TaskId(id));
	}
	
	@PatchMapping(value = "/{id}")
	TaskResponse update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
		var output = updateTaskUseCase.execute(new TaskId(id), request.toInput());
		return TaskResponse.from(output);
	}
}
