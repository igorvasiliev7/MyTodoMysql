package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private Button btbOk;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private DatePicker date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.setItems(FXCollections.observableArrayList(getChoices()));

        btbOk.setOnAction(event -> printValues());
    }

    private void printValues() {
        final String item = choiceBox.getSelectionModel().getSelectedItem();
        System.out.println(item);
        System.out.println(date.getValue());
    }

    private List<String> getChoices() {
        return Arrays.asList("FIRST", "SECOND", "THIRD");
    }
}
