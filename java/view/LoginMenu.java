package view;

import controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class LoginMenu extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        new AppController();
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        LoginMenu.stage = primaryStage;
        Pane loginPane = FXMLLoader.load(new URL
                (Objects.requireNonNull(Game.class.getResource("/fxml/LoginMenu.fxml")).toExternalForm()));
        Scene scene = new Scene(loginPane);
        primaryStage.setTitle("LoginMenu");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(LoginMenu.class.getResourceAsStream
                ("/image/aa.png"))));
        primaryStage.setScene(scene);
        stage.show();
    }
}
