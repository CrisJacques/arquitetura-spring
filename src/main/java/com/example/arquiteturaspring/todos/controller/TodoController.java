package com.example.arquiteturaspring.todos.controller;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import com.example.arquiteturaspring.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("todos")// A / é opcional no RequestMapping (ou seja, tanto faz "/todos" ou "todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity<TodoEntity> save(@RequestBody TodoEntity todoEntity) {
        return ResponseEntity.ok(service.salvar(todoEntity));
    }

}
