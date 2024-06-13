package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class SettingMenu extends Application {

    public static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = SettingMenu.class.getResource("/FXML/SettingMenu.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        scene = new Scene(pane);
        primaryStage.setTitle("Setting");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
