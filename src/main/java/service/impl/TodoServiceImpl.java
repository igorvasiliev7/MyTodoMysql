package service.impl;

import dao.TodoDao;
import dao.factory.DaoFactory;
import model.Todo;
import service.TodoService;

import java.sql.SQLException;
import java.util.List;

public class TodoServiceImpl implements TodoService {
       private TodoDao todoDao = DaoFactory.getTodoDao();

    @Override
    public void save(Todo todo) {
        try {
            todoDao.save(todo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Todo> findAll() {
        try {
            return todoDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Todo findTheLastOne(){
        try {
            return todoDao.findTheLastOne();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id){
        try {
            todoDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
