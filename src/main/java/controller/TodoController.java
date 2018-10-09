package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Todo;
import service.factory.ServiceFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAdd;
    @FXML
    private TableColumn<Todo, String> txtStatus;
    @FXML
    private TableColumn<Todo, String> dateFinish;
    @FXML
    private TableColumn<Todo, String> dateCreation;
    @FXML
    private TableColumn<Todo, String> txtName;
    @FXML
    private TableView<Todo> todosTable;

    private ObservableList<Todo> todoList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        loadTodos();
        btnAdd.setOnAction(this::add);
        btnDelete.setOnAction(event -> delete());
    }

    private void init() {
        txtStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        dateFinish.setCellValueFactory(new PropertyValueFactory<>("dateFinish"));
        txtName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadTodos() {
        final List<Todo> todos = ServiceFactory.getTodoService().findAll();
        todoList.addAll(todos);
        todosTable.setItems(todoList);
    }

    private void add(ActionEvent event) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/addTodo.fxml"));
            final Parent parent = loader.load();

            final Stage stage = new Stage();
            final Scene scene = new Scene(parent);
            scene.getStylesheets().add("css/main.css");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            Window window = ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(window);
            stage.show();

            stage.setOnHiding(e -> {
               todoList.add(0,findTheLastOne());
               todosTable.setItems(todoList);
                // TODO: 24.09.2018 first
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Todo findTheLastOne() {
            return ServiceFactory.getTodoService().findTheLastOne();
    }

    private void delete() {
        final Todo todo = todosTable.getSelectionModel().getSelectedItem();
        ServiceFactory.getTodoService().delete(todo.getId());
        todoList.remove(todo);
    }
}
