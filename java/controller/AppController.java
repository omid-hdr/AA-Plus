package controller;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Database;
import model.GameDataBase;
import model.User;

import java.util.ArrayList;
import java.util.Objects;

public class AppController {
    private static Database database;
    private static GameDataBase gameDataBase;
    private static User currentUser;
    private static Media sound1;
    private static Media sound2;
    private static Media sound3;
    private static MediaPlayer mediaPlayer;
    private static boolean isBlackWhite;
    private static String shoot1;
    private static String shoot2;
    private static String freeze;
    private static boolean Player2;


    public AppController() {
        database = new Database();
        database.loadDataFromFile();
        isBlackWhite = false;
        shoot1 = "Space";
        freeze = "Tab";
        shoot2 = "Enter";
        sound1 = new Media(Objects.requireNonNull(AppController.class.getResource("/sound/Pat.mp3")).toExternalForm());
        sound2 = new Media(Objects.requireNonNull(AppController.class.getResource("/sound/Lion.mp3")).toString());
        sound3 = new Media(Objects.requireNonNull(AppController.class.getResource("/sound/Palang.mp3")).toString());
        mediaPlayer = new MediaPlayer(sound1);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        AppController.currentUser = currentUser;
    }

    public static Database getDatabase() {
        return database;
    }

    public static GameDataBase getGameDataBase() {
        return gameDataBase;
    }

    public static void setGameDataBase(GameDataBase gameDataBase) {
        AppController.gameDataBase = gameDataBase;
    }

    public static boolean isBlackWhite() {
        return isBlackWhite;
    }

    public static void setBlackWhite(boolean blackWhite) {
        isBlackWhite = blackWhite;
    }

    public static void changeMusic(int song) {
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(getMedia(song));
        mediaPlayer.setAutoPlay(true);
    }

    public static boolean isPlayer2() {
        return Player2;
    }

    public static void setPlayer2(boolean player2) {
        Player2 = player2;
    }

    public static void stopMusic() {
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }

    private static Media getMedia(int song) {
        if (1 == song)
            return sound1;
        else if (2 == song)
            return sound2;
        else
            return sound3;
    }

    public static void mute(boolean isMute) {
        mediaPlayer.setMute(isMute);
    }

    public static String getShoot1() {
        return shoot1;
    }

    public static void setShoot1(String shoot1) {
        AppController.shoot1 = shoot1;
    }

    public static VBox table(int level) {
        ArrayList<User> users = database.sort(level);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        HBox hBox1 = new HBox();
        hBox1.setSpacing(100);
        hBox1.setPrefHeight(20);
        Text name1 = new Text("Name");
        Text score1 = new Text("Score");
        Text time1 = new Text("Time");
        hBox1.getChildren().addAll(name1, score1, time1);
        vBox.getChildren().add(hBox1);
        for (int i = 0; i < Math.min(10, users.size()); i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(100);
            hBox.setPrefHeight(20);
            Text name = new Text(users.get(i).getUsername());
            Text score = new Text(String.valueOf(users.get(i).getHighScore(level)));
            Text time = new Text(String.valueOf(users.get(i).getHighScoreTime(level)));
            if (i == 0) {
                name.setFill(Color.GOLD);
                score.setFill(Color.GOLD);
                time.setFill(Color.GOLD);
            } else if (i == 1) {
                score.setFill(Color.SILVER);
                name.setFill(Color.SILVER);
                time.setFill(Color.SILVER);
            } else if (i == 2) {
                score.setFill(Color.BROWN);
                time.setFill(Color.BROWN);
                name.setFill(Color.BROWN);
            }
            hBox.getChildren().addAll(name, score, time);
            vBox.getChildren().add(hBox);
        }
        return vBox;
    }

    public static String getShoot2() {
        return shoot2;
    }

    public static void setShoot2(String shoot2) {
        AppController.shoot2 = shoot2;
    }

    public static String getFreeze() {
        return freeze;
    }

    public static void setFreeze(String freeze) {
        AppController.freeze = freeze;
    }

    public static void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
