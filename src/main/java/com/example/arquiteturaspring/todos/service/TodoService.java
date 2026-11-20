package com.example.arquiteturaspring.todos.service;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import com.example.arquiteturaspring.todos.repository.TodoRepository;
import com.example.arquiteturaspring.todos.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    public TodoEntity salvar(TodoEntity novoTodo) {
        return repo.save(novoTodo);
    }

    /**
     * É importante setar o id no objeto antes de passar ele para o método save(), porque se o objeto for
     * passado sem o id o JPA vai entender que é para criar um registro novo no banco de dados ao invés de
     * atualizar um já existente.
     */
    public void atualizar(Integer id, TodoEntity todo) {
        buscarPorId(id); // Lança exceção se não encontrar o objeto a ser atualizado
        todo.setId(id);
        repo.save(todo);
    }

    public TodoEntity buscarPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Todo não encontrado"));
    }

    public List<TodoEntity> buscarTodos() {
        return repo.findAll();
    }

    public void remover(Integer id) {
        buscarPorId(id);// Lança exceção se não encontrar o objeto a ser removido
        repo.deleteById(id);
    }

}
