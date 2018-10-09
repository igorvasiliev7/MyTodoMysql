package dao.factory;

import dao.TodoDao;
import dao.UserDao;
import dao.impl.TodoDaoImpl;
import dao.impl.UserDaoImpl;

public class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static TodoDao getTodoDao() {
        return new TodoDaoImpl();
    }
}
