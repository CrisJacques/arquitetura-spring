package com.example.arquiteturaspring.todos.repository;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
}
