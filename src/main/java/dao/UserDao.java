package dao;

import model.User;

import java.sql.SQLException;

public interface UserDao {

    User findByEmail(String email) throws SQLException;

    void save(User user) throws SQLException;
}
