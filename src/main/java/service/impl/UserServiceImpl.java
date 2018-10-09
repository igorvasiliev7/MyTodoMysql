package service.impl;

import dao.factory.DaoFactory;
import model.User;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public User findByEmail(String email) {
        try {
            return DaoFactory.getUserDao().findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(User user) {
        try {
            DaoFactory.getUserDao().save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
