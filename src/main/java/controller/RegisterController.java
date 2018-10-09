package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import service.UserService;
import service.factory.ServiceFactory;

import start.AppManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private PasswordField rePassword;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnBackToLogin;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField txtEmail;

    private static final String TODO_NAME = "todos.fxml";
    private static final String LOGIN_NAME = "login.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCreate.setOnAction(event -> registration());
        btnBackToLogin.setOnAction(event -> action(LOGIN_NAME));
    }

    private void registration() {
        if (!txtName.getText().isEmpty() && !txtEmail.getText().isEmpty() && !password.getText().isEmpty()) {
        } else {
            System.out.println("Fill all fields");
            return;
        }

        if (password.getText().equals(rePassword.getText())) {
        } else {
            System.out.println("Passwords don`t match");
            return;
        }
        UserService userServiceImpl = ServiceFactory.getUserService();
        final User userByEmail = userServiceImpl.findByEmail(txtEmail.getText());
        if (userByEmail == null) {
            User user = new User();
            user.setName(txtName.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(password.getText());
            userServiceImpl.save(user);
            action(TODO_NAME);
        } else {
            System.out.println("User with such email has already registered");
        }
    }

    private void action(String path) {
        try {
            new AppManager().getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/" + path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
