package dao.impl;

import dao.UserDao;
import datasource.DatabaseSource;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private static final String INSERT_USER = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
    private static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    @Override
    public User findByEmail(String email){
        User user = null;
        try (Connection connection = DatabaseSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        try (Connection connection = DatabaseSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
