package br.com.andrepontes.todolist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrepontes.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	Page<Task> findByProjectName(String projectName, Pageable pageable);

}
