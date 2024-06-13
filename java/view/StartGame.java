package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class StartGame extends Application {
    private static Pane pane;

    public static Pane getPane() {
        return pane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = SettingMenu.class.getResource("/FXML/StartGame.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Start Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
