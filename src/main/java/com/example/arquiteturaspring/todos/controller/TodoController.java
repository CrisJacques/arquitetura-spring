package com.example.arquiteturaspring.todos.controller;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import com.example.arquiteturaspring.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos")// A / é opcional no RequestMapping (ou seja, tanto faz "/todos" ou "todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity<TodoEntity> save(@RequestBody TodoEntity todoEntity) {
        return ResponseEntity.ok(service.salvar(todoEntity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarTodo(@PathVariable Integer id, @RequestBody TodoEntity todo) {
        service.atualizar(id, todo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoEntity> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TodoEntity>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
