package dao;

import model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDao {

    void save(Todo todo) throws SQLException;

    List<Todo> findAll() throws SQLException;

    void delete(Long id) throws SQLException;

    Todo findTheLastOne() throws SQLException;
}
