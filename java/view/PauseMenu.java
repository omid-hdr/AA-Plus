package view;

import controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class PauseMenu extends Application {
    public static Stage stage;
    public ComboBox<String> selectMusic;
    public Label shoot;
    public Label shoot2;
    public Label freeze;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        stage.initOwner(LoginMenu.stage);
        URL url = MainMenu.class.getResource("/fxml/PauseMenu.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setTitle("Pause Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        selectMusic.getItems().add("Pink Pattern");
        selectMusic.getItems().add("Pat");
        selectMusic.getItems().add("LionKing");
        selectMusic.setValue("Pat");
        shoot.setText("shoot1 Key" + AppController.getShoot1());
        shoot2.setText("shoot2 Key" + AppController.getShoot2());
        freeze.setText("freeze Key" + AppController.getFreeze());
    }


    public void resume() {
        stage.close();
        Game.play();
    }

    public void restart() throws Exception {
        new StartGame().start(LoginMenu.stage);
        stage.close();
    }

    private int getSound(String value) {
        return switch (value) {
            case "Pat" -> 1;
            case "Pink Pattern" -> 3;
            default -> 2;
        };
    }

    public void setMute() {
        AppController.stopMusic();
    }

    public void setSelectMusic() {
        AppController.changeMusic(getSound(selectMusic.getValue()));
    }
}
