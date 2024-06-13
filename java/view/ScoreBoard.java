package view;

import controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class ScoreBoard extends Application {

    public static Stage stage;
    public static Pane pane;
    public static VBox box;
    public ComboBox<String> level;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        stage.initOwner(LoginMenu.stage);
        URL url = MainMenu.class.getResource("/fxml/ScoreBoard.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);
        box = AppController.table(1);
        box.setAlignment(Pos.CENTER);
        box.setLayoutX(150);
        box.setLayoutY(100);
        pane.getChildren().add(box);
        Scene scene = new Scene(pane);
        stage.setTitle("Score Board");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        level.getItems().add("Easy");
        level.getItems().add("Medium");
        level.getItems().add("Hard");
        level.setValue("Medium");
    }


    public void mainMenu() throws Exception {
        new MainMenu().start(LoginMenu.stage);
        stage.close();
    }

    public void startGame(

    ) throws Exception {
        new StartGame().start(LoginMenu.stage);
        stage.close();
    }

    public void resetLevel() {
        pane.getChildren().remove(box);
        box = AppController.table(handle(level.getValue()));
        box.setAlignment(Pos.CENTER);
        box.setLayoutX(150);
        box.setLayoutY(100);
        pane.getChildren().add(box);
    }

    private int handle(String value) {
        switch (value) {
            case "Easy" -> {
                return 0;
            }
            case "Hard" -> {
                return 2;
            }
            default -> {
                return 1;
            }
        }
    }
}
