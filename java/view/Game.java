package view;

import controller.AppController;
import controller.GameController;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.Circle;
import model.Rocket;

import java.net.URL;
import java.util.Objects;

public class Game extends Application {

    private static final int COUNTDOWN_DURATION = 120; // Countdown duration in seconds
    public static Rocket rocket;
    public static Label ballLeft;
    public static Label score;
    private static Circle circle;
    private static Timeline timeline = null;
    private static GameController aa;
    private static int remainingTime = COUNTDOWN_DURATION;
    private ProgressBar progressBar;
    private Label timeLabel;
    private Rocket rocket1;

    public static void win(Pane game) {
        EndGameMenu.setIsWin(true);
        EndGameMenu.setScore1(score.getText());
        EndGameMenu.setTime1(remainingTime);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000),
                actionEvent -> {
                    try {
                        end();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        timeline.setCycleCount(1);
        timeline.play();
        pause();
        if (!AppController.isBlackWhite()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(-0.5);
            game.setEffect(colorAdjust);
        }
        WinAnimation winAnimation = new WinAnimation(game);
        winAnimation.play();
        if (AppController.getCurrentUser() != null)
            AppController.getCurrentUser().checkRecord(Integer.parseInt(score.getText()),
                    AppController.getGameDataBase().getLevel() - 1, remainingTime);
        AppController.getDatabase().saveDataIntoFile();
    }

    public static void lose(Pane game) {
        EndGameMenu.setIsWin(false);
        EndGameMenu.setScore1(score.getText());
        EndGameMenu.setTime1(remainingTime);
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(3000),
                actionEvent -> {
                    try {
                        end();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }));
        timeline1.setCycleCount(1);
        timeline1.play();
        pause();
        if (!AppController.isBlackWhite()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(1);
            game.setEffect(colorAdjust);
        }
        LoseAnimation loseAnimation = new LoseAnimation(game);
        loseAnimation.play();
        WinAnimation winAnimation = new WinAnimation(game);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000),
                actionEvent -> winAnimation.play()));
        timeline.setCycleCount(1);
        timeline.play();
    }

    public static void pause() {
        timeline.pause();
    }

    public static void play() {
        timeline.play();
    }

    public static void handleScore() {
        int scoreNumber = 2 * (AppController.getGameDataBase().getAllBalls() - AppController.getGameDataBase().
                getBallsLeft());
        score.setText(String.valueOf(scoreNumber));
    }

    private static void createBallLeft() {
        ballLeft = new Label();
        ballLeft.setLayoutX(550);
        ballLeft.setLayoutY(10);
        ballLeft.setFont(new Font(17));
        handleBallLeft();
    }

    public static void handleBallLeft() {
        int ballsLeft = AppController.getGameDataBase().getBallsLeft();
        ballLeft.setText(String.valueOf(ballsLeft));
        if (ballsLeft <= 3)
            ballLeft.setTextFill(Color.RED);
        else if (ballsLeft <= 6)
            ballLeft.setTextFill(Color.YELLOW);
        else
            ballLeft.setTextFill(Color.RED);
    }

    private static void handleBlackAndWhite(Pane game) {
        if (AppController.isBlackWhite()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            game.setEffect(colorAdjust);
        }
    }

    public static Circle getCircle() {
        return circle;
    }

    private static void end() throws Exception {
        aa.finish();
        remainingTime = 120;
        Ball.setR(130);
        EndGameMenu endGameMenu = new EndGameMenu();
        endGameMenu.start(LoginMenu.stage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        createBallLeft();
        createScore();
        aa = new GameController();
        URL url = Game.class.getResource("/fxml/game.fxml");
        assert url != null;
        Pane game = FXMLLoader.load(url);
        handleBlackAndWhite(game);
        timer(game);
        Scene scene = new Scene(game);
        circle = new Circle();
        Rocket rocket = createRocket(game);
        progressBar = new ProgressBar(0);
        progressBar.setPadding(new Insets(10));
        game.getChildren().addAll(circle, rocket, progressBar, timeLabel, rocket.getLine(), ballLeft, score);
        game.getChildren().get(1).requestFocus();
        game.getChildren().addAll(AppController.getGameDataBase().getBalls());
        if (AppController.isPlayer2()) {
            rocket1 = new Rocket();
            rocket1.setFill(new ImagePattern(
                    new Image(Objects.requireNonNull(Rocket.class.getResource("/image/Rocket1.png")).
                            toExternalForm())));
            rocket1.setCenterY(20);
            rocket1.setRadius(25);
            game.getChildren().addAll(rocket1, rocket1.getLine());
        }
        TurningRound.setTurnBase(AppController.getGameDataBase().getSpeed());
        primaryStage.setTitle("aa");
        primaryStage.setScene(scene);
        aa.rotate(game);
        primaryStage.show();
    }

    private void createScore() {
        score = new Label();
        score.setLayoutX(850);
        score.setLayoutY(10);
        score.setFont(new Font(20));
        score.setTextFill(Color.CRIMSON);
        handleScore();
    }

    private void timer(Pane game) {
        timeLabel = new Label(formatTime(remainingTime));
        timeLabel.setStyle("-fx-font-size: 48px;");
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    remainingTime--;
                    if (remainingTime >= 0) {
                        timeLabel.setText(formatTime(remainingTime));
                    } else {
                        timeline.stop();
                        lose(game);
                    }
                })
        );
        timeline.setCycleCount(COUNTDOWN_DURATION + 1);
        timeline.play();
        timeLabel.setLayoutX(100);
        timeLabel.setTextFill(Color.BISQUE);
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private Rocket createRocket(Pane gamePane) {
        rocket = new Rocket();
        rocket.setOnKeyPressed(keyEvent -> {
            String keyName = keyEvent.getCode().getName();
            if (keyName.equals(AppController.getShoot1())) {
                AppController.getGameDataBase().setPlayer2Shoot(false);
                aa.shoot(gamePane, rocket, rocket1);
                progressBar.setProgress(progressBar.getProgress() + 0.2);
            } else if (keyName.equals("Left"))
                aa.moveLeft(rocket);
            else if (keyName.equals("Right"))
                aa.moveRight(rocket);
            else if (keyName.equals("P")) {
                try {
                    runPauseMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (keyName.equals(AppController.getFreeze()))
                freeze();
            else if (AppController.isPlayer2() && keyName.equals(AppController.getShoot2())) {
                progressBar.setProgress(progressBar.getProgress() + 0.2);
                AppController.getGameDataBase().setPlayer2Shoot(true);
                aa.shoot(gamePane, rocket, rocket1);
            } else if (keyName.equals("A"))
                aa.moveLeft(rocket1);
            else if (keyName.equals("D"))
                aa.moveRight(rocket1);
        });
        return rocket;
    }

    private void runPauseMenu() throws Exception {
        PauseMenu pauseMenu = new PauseMenu();
        pauseMenu.start(LoginMenu.stage);
    }

    private void freeze() {
        System.out.println(AppController.getGameDataBase().getBalls().get(0).getCenterX());
        System.out.println(AppController.getGameDataBase().getBalls().get(0).getCenterY());
        System.out.println(AppController.getGameDataBase().getBalls().get(0).getDegree());
        circle.setFill(new ImagePattern(
                new Image(Objects.requireNonNull(Circle.class.getResource("/image/freeze.jpg")).toExternalForm())));
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(circle);
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setFromValue(0.3);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        if (1 == progressBar.getProgress()) {
            TurningRound.setTurnBase(0.006);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(AppController.getGameDataBase().getFreezeTime()),
                    actionEvent -> {
                        TurningRound.setTurnBase(AppController.getGameDataBase().getSpeed());
                        circle.setOpacity(1);
                        circle.setFill(new ImagePattern(
                                new Image(Objects.requireNonNull(Circle.class.getResource("/image/earth.jpg")).toExternalForm())));
                    }));
            timeline.setCycleCount(1);
            timeline.play();
            progressBar.setProgress(0);
        }
        MediaPlayer mediaPlayer;
        Media freeze = new Media(Objects.requireNonNull(AppController.class.getResource("/sound/freeze.mp3")).toString());
        mediaPlayer = new MediaPlayer(freeze);
        mediaPlayer.play();
    }
}
