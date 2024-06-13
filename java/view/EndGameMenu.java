package view;

import controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;

public class EndGameMenu extends Application {

    public static Stage stage;

    @FXML
    public static Pane pane;
    public static VBox box;
    private static int time1;
    private static String score1;
    private static boolean isWin = false;
    public Label time;
    public Label state;
    public Label score;

    public static void setIsWin(boolean isWin) {
        EndGameMenu.isWin = isWin;
    }

    public static void setTime1(int time1) {
        EndGameMenu.time1 = time1;
    }

    public static void setScore1(String score1) {
        EndGameMenu.score1 = score1;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        stage.initOwner(LoginMenu.stage);
        URL url = EndGameMenu.class.getResource("/fxml/EndGame.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);
        box = AppController.table(AppController.getGameDataBase().getLevel() - 1);
        box.setAlignment(Pos.CENTER);
        box.setLayoutX(150);
        box.setLayoutY(100);
        pane.getChildren().add(box);
        Scene scene = new Scene(pane);
        if (isWin) pane.setBackground(Background.fill(Color.GREEN));
        else pane.setBackground(Background.fill(Color.RED));
        stage.setTitle("End Game Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        if (isWin) state.setText("You Win");
        else state.setText("You Lost");
        time.setText(String.valueOf(time1));
        score.setText(score1);
    }

    public void mainMenu() throws Exception {
        new MainMenu().start(LoginMenu.stage);
        stage.close();
    }

    public void startGame() throws Exception {
        new StartGame().start(LoginMenu.stage);
        stage.close();
    }
}

