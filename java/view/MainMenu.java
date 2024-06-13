package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = MainMenu.class.getResource("/fxml/MainMenu.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("MainMenu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
