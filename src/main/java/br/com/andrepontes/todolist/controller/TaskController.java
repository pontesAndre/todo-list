package br.com.andrepontes.todolist.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.andrepontes.todolist.controller.dto.TaskDetailDto;
import br.com.andrepontes.todolist.controller.dto.TaskDto;
import br.com.andrepontes.todolist.controller.form.TaskForm;
import br.com.andrepontes.todolist.controller.form.UpdateTaskForm;
import br.com.andrepontes.todolist.model.StatusTask;
import br.com.andrepontes.todolist.model.Task;
import br.com.andrepontes.todolist.repository.ProjectRepository;
import br.com.andrepontes.todolist.repository.TaskRepository;

@RestController
@RequestMapping("/todo")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@GetMapping
	@Cacheable(value = "listTask")
	public Page<TaskDto> listTask(@RequestParam(required = false) String projectName, Pageable pageable) {
		return (projectName == null) ? TaskDto.process(taskRepository.findAll(pageable))
				: TaskDto.process(taskRepository.findByProjectName(projectName, pageable));
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listTask", allEntries = true)
	public ResponseEntity<TaskDto> registerTask(@RequestBody @Valid TaskForm taskForm,
			UriComponentsBuilder uriBuilder) {
		Task task = taskForm.process(projectRepository);
		taskRepository.save(task);
		URI uri = uriBuilder.path("/todo/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(task));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDetailDto> detailTask(@PathVariable Long id) {
		Optional<Task> task = taskRepository.findById(id);
		return (task.isPresent()) ? ResponseEntity.ok(new TaskDetailDto(task.get()))
				: ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listTask", allEntries = true)
	public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody @Valid UpdateTaskForm updateTaskForm) {
		Optional<Task> optional = taskRepository.findById(id);
		return (optional.isPresent()) ? ResponseEntity.ok(new TaskDto(updateTaskForm.update(optional.get())))
				: ResponseEntity.notFound().build();

	}

	@PatchMapping("{id}")
	@Transactional
	@CacheEvict(value = "listTask", allEntries = true)
	public ResponseEntity<?> completedTask(@PathVariable Long id) {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isPresent()) {
			Task task = optional.get();
			task.setStatus(StatusTask.COMPLETED);
			return ResponseEntity.ok(new TaskDto(task));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listTask", allEntries = true)
	public ResponseEntity<?> removeTask(@PathVariable Long id) {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isPresent()) {
			taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
