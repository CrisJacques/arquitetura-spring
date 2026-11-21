package com.example.arquiteturaspring.todos.repository;

import com.example.arquiteturaspring.todos.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Aqui não há necessidade de utilizar a anotação @Repository porque esta interface estende de JPA, que já possui
 * essa anotação.
 *
 * Classes com @Repository são otimizadas pelo Spring para trabalhar com banco de dados. Dessa forma, não poderiam
 * receber a anotação genérica @Component.
 */
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    boolean existsByDescricao(String descricao);

}
