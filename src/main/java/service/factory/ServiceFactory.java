package service.factory;

import service.TodoService;
import service.UserService;
import service.impl.TodoServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static TodoService getTodoService() {
        return new TodoServiceImpl();
    }
}
