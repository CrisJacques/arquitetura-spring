package com.example.arquiteturaspring.todos.service;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import com.example.arquiteturaspring.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    public TodoEntity salvar(TodoEntity novoTodo) {
        return repo.save(novoTodo);
    }

}
