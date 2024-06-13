package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import model.GameDataBase;
import view.Game;
import view.LoginMenu;

import java.util.ArrayList;

public class StartGameController {
    public Slider slider;
    public ComboBox<String> comboBox;
    private int map;

    @FXML
    public void initialize() {
        map = 2;
        comboBox.getItems().add("Easy");
        comboBox.getItems().add("Medium");
        comboBox.getItems().add("Hard");
        comboBox.setValue("Medium");
        slider.setMin(4);
        slider.setMax(12);
        slider.setValue(6);
        slider.setBlockIncrement(1);
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(1);
        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
    }

    public void startNewGame() throws Exception {
        AppController.setGameDataBase(new GameDataBase(getLevel(comboBox.getValue()), (int) slider.getValue(),
                new ArrayList<>(), map));
        AppController.getGameDataBase().setAllBalls((int) slider.getValue());
        AppController.getGameDataBase().setBallsLeft((int) slider.getValue());
        AppController.getGameDataBase().setLevel(getLevel(comboBox.getValue()));
        new Game().start(LoginMenu.stage);
    }


    private int getLevel(String value) {
        return switch (value) {
            case "Easy" -> 1;
            case "Medium" -> 2;
            default -> 3;
        };
    }

    public void set1() {
        map = 1;
    }

    public void set2() {
        map = 2;
    }

    public void set3() {
        map = 3;
    }
}
