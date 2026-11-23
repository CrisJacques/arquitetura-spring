package com.example.arquiteturaspring.todos.service.util;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import com.example.arquiteturaspring.todos.repository.TodoRepository;
import com.example.arquiteturaspring.todos.service.exception.TodoAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    @Autowired
    private TodoRepository repo;

    public void validarDescricao(TodoEntity todo) {
        if(existeTodoComDescricao(todo.getDescricao())){
            throw new TodoAlreadyExistsException("Já existe um TODO com esta descrição.");
        }
    }

    private boolean existeTodoComDescricao(String descricao) {
        return repo.existsByDescricao(descricao);
    }

}
