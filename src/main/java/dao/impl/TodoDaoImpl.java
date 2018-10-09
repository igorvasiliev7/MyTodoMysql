package dao.impl;

import dao.TodoDao;
import datasource.DatabaseSource;
import model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TodoDaoImpl implements TodoDao {

    private static final String INSERT_TODO = "INSERT INTO todos (name, datecreation, status, datefinish) VALUES (?,?,?,?)";
    private static final String FIND_ALL = "SELECT * FROM todos";
    private static final String DELETE = "DELETE FROM todos WHERE id=?";
    private static final String FIND_THE_LAST = "SELECT * FROM todos ORDER BY id DESC";

    @Override
    public void save(Todo todo) {
        try (Connection connection = DatabaseSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO)) {
            preparedStatement.setString(1, todo.getName());
            preparedStatement.setString(2, todo.getDateCreation());
            preparedStatement.setString(3, todo.getStatus());
            preparedStatement.setString(4, todo.getDateFinish());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Todo> findAll() {

        List<Todo> todos = new LinkedList<>();
        try (Connection connection = DatabaseSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            Todo todo = new Todo();
            todo.setId(resultSet.getLong("id"));
            todo.setDateCreation(resultSet.getString("datecreation"));
            todo.setDateFinish(resultSet.getString("datefinish"));
            todo.setStatus(resultSet.getString("status"));
            todo.setName(resultSet.getString("name"));
            todos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DatabaseSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Todo findTheLastOne() {
        Todo todo = new Todo();
        try (Connection connection = DatabaseSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_THE_LAST)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            todo.setId(resultSet.getLong("id"));
            todo.setName(resultSet.getString("name"));
            todo.setDateCreation(resultSet.getString("datecreation"));
            todo.setDateFinish(resultSet.getString("datefinish"));
            todo.setStatus(resultSet.getString("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }
}
