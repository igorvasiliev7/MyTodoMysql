package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppManager extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX MySQL");
        primaryStage.show();
    }

    public Stage getStage() {
        return stage;
    }
}
