package br.com.andrepontes.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrepontes.todolist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);

}
