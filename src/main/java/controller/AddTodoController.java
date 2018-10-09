package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Todo;
import service.factory.ServiceFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddTodoController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btnAdd;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private DatePicker dateFinish;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private TextField txtName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.setItems(FXCollections.observableArrayList(getStatus()));
        btnAdd.setOnAction(event -> add());
    }

    private void add() {
        final String name = txtName.getText();
        final LocalDate dateCreationValue = dateCreation.getValue();
        final LocalDate dateFinishValue = dateFinish.getValue();
        final String selectedStatus = choiceBox.getSelectionModel().getSelectedItem();

        final Todo todo = new Todo();
        todo.setName(name);
        todo.setDateCreation(dateCreationValue.toString());
        todo.setDateFinish(dateFinishValue.toString());
        todo.setStatus(selectedStatus);

        ServiceFactory.getTodoService().save(todo);

        root.getScene().getWindow().hide();
//        txtName.clear();

    }

    private List<String> getStatus() {
        return Arrays.asList("OK", "NORMAL", "SUPER");
    }
}
