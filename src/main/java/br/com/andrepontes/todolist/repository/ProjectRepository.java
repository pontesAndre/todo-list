package br.com.andrepontes.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrepontes.todolist.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Project findByName(String projectName);

}
