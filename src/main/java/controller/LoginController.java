package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import service.factory.ServiceFactory;
import start.AppManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button btnRegistr;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(event -> login());
        btnRegistr.setOnAction(event -> registration());
    }

    private void registration() {
        try {
            new AppManager().getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registration.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        final String email = txtEmail.getText();
        final String password = txtPassword.getText();
        if ("".equals(email) || "".equals(password)) {
            System.out.println("Email or password is empty");
            return;
        }

        final User user = ServiceFactory.getUserService().findByEmail(email);
        if (user == null) {
            System.out.println("User is null");
            return;
        }

        if (!password.equals(user.getPassword())) {
            System.out.println("passwords don't match");
            return;
        }

        try {
            new AppManager().getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/todos.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
