package service;

import model.Todo;

import java.util.List;

public interface TodoService {

    void save(Todo todo);

    List<Todo> findAll();

    Todo findTheLastOne();

    void delete(Long id);
}
