package com.example.arquiteturaspring.todos.service;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import com.example.arquiteturaspring.todos.repository.TodoRepository;
import com.example.arquiteturaspring.todos.service.exception.ObjectNotFoundException;
import com.example.arquiteturaspring.todos.service.util.MailSender;
import com.example.arquiteturaspring.todos.service.util.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Aqui poderia ser usada a anotação @Component também, o comportamento seria exatamente o mesmo.
 * Porém, para Services é recomendado usar a anotação @Service por questão de semântica.
 */
@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    @Autowired
    private TodoValidator validator;

    @Autowired
    private MailSender mailSender;

    public TodoEntity salvar(TodoEntity novoTodo) {
        validator.validarDescricao(novoTodo); // Lança exceção se já existe um todo com a mesma descrição

        return repo.save(novoTodo);
    }

    /**
     * É importante setar o id no objeto antes de passar ele para o método save(), porque se o objeto for
     * passado sem o id o JPA vai entender que é para criar um registro novo no banco de dados ao invés de
     * atualizar um já existente.
     */
    public void atualizar(Integer id, TodoEntity todo) {
        buscarPorId(id); // Lança exceção se não encontrar o objeto a ser atualizado
        validator.validarDescricao(todo); // Lança exceção se já existe um todo com a mesma descrição

        todo.setId(id);
        repo.save(todo);

        String status = todo.getConcluido() == Boolean.TRUE ? "Concluído" : "Não concluído";
        mailSender.enviar("Todo " + todo.getDescricao() + " foi atualizado para " + status);
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
