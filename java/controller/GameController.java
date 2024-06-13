package controller;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Ball;
import model.Circle;
import model.Rocket;
import view.*;

import java.util.ArrayList;
import java.util.Objects;

public class GameController {
    public static boolean phase2 = false;
    public static boolean phase3 = false;
    public static boolean phase4 = false;
    public static TurningRound turningRound;
    public static Phase2Animation phase2Animation;
    private static Pane pane;
    private static Timeline timeline3;
    private static Timeline timeline4;
    private static Timeline timeline5;
    private static Timeline timeline7;
    public Pane mainPane;

    public static void intersects() {
        ArrayList<Ball> balls = AppController.getGameDataBase().getBalls();
        for (int i = 0; i < balls.size() - 1; i++) {
            if (checkIntersects(balls.get(i), balls.get(balls.size() - 1))) {
                balls.get(i).setFill(Color.RED);
                Game.lose(pane);
                turningRound.stop();
                return;
            }
        }
        if (0 == AppController.getGameDataBase().getBallsLeft()) {
            Game.win(pane);
            turningRound.stop();
        }
    }

    private static boolean checkIntersects(Ball ball, Ball ball1) {
        return (Math.pow(ball.getCenterX() - ball1.getCenterX(), 2) +
                Math.pow(ball.getCenterY() - ball1.getCenterY(), 2)) < Math.pow(ball.getRadius() * 2, 2);
    }

    public static void successfulShoot() {
        Circle circle = Game.getCircle();
        circle.setFill(Color.BISQUE);
        circle.setRadius(55);
        Timeline timeline6 = new Timeline(new KeyFrame(Duration.millis(300), actionEvent -> {
            circle.setFill(new ImagePattern(
                    new Image(Objects.requireNonNull(Circle.class.getResource("/image/earth.jpg")).toExternalForm())));
            circle.setRadius(60);
        }));
        timeline6.setCycleCount(1);
        timeline6.play();
    }

    public void moveLeft(Rocket rocket) {
        if (rocket.getCenterX() > 200) rocket.setCenterX(rocket.getCenterX() - 15);
        rocket.checkLine();
    }

    public void moveRight(Rocket rocket) {
        if (rocket.getCenterX() < 700) rocket.setCenterX(rocket.getCenterX() + 15);
        rocket.checkLine();
    }

    public void shoot(Pane gamePane, Rocket rocket, Rocket rocket1) {
        Ball ball = new Ball();
        Game.handleBallLeft();
        Game.handleScore();
        gamePane.getChildren().add(ball);
        if (AppController.getGameDataBase().isPlayer2Shoot()) {
            ball.setFill(new ImagePattern(
                    new Image(Objects.requireNonNull(Circle.class.getResource("/image/Spherical.jpg")).toExternalForm())));
            ShootingAnimation1 shootingAnimation1 = new ShootingAnimation1(gamePane, ball, rocket1);
            shootingAnimation1.play();
        } else {
            ShootingAnimation shootingAnimation = new ShootingAnimation(gamePane, ball);
            shootingAnimation.play();
            RotateTransition transition = new RotateTransition();
            transition.setNode(rocket);
            transition.setDuration(Duration.millis(500));
            transition.setFromAngle(0);
            transition.setToAngle(360);
            transition.play();
        }
        if (!phase2 && ((double) AppController.getGameDataBase().getBallsLeft()) /
                ((double) AppController.getGameDataBase().getAllBalls()) <= 0.75) {
            phase2 = true;
            phase2();
            phase2Animation = new Phase2Animation();
            phase2Animation.play();
        } else if (!phase3 && ((double) AppController.getGameDataBase().getBallsLeft()) /
                ((double) AppController.getGameDataBase().getAllBalls()) <= 0.5)
            phase3();
        else if (!phase4 && ((double) AppController.getGameDataBase().getBallsLeft()) /
                ((double) AppController.getGameDataBase().getAllBalls()) <= 0.25)
            phase4(rocket, rocket1);
    }

    private void phase4(Rocket rocket, Rocket rocket1) {
        phase4 = true;
        timeline5 = new Timeline(new KeyFrame(Duration.seconds
                (5 - AppController.getGameDataBase().getLevel()), actionEvent -> {
            AppController.getGameDataBase().setDegree(Math.random() * Ball.PI / 6 + 5 * Ball.PI / 12);
            rocket.checkLine();
            if (AppController.isPlayer2())
                rocket1.checkLine();
        }));
        timeline5.setCycleCount(-1);
        timeline5.setDelay(Duration.millis(1000));
        timeline5.play();
    }

    private void phase3() {
        phase3 = true;
        timeline3 = new Timeline(new KeyFrame(Duration.millis(2000), actionEvent -> {
            for (Ball ball : AppController.getGameDataBase().getBalls()) {
                ball.visible();
            }
        }));
        timeline3.setCycleCount(-1);
        timeline3.play();
        timeline4 = new Timeline(new KeyFrame(Duration.millis(2000), actionEvent -> {
            for (Ball ball : AppController.getGameDataBase().getBalls()) {
                ball.unVisible();
            }
        }));
        timeline4.setCycleCount(-1);
        timeline4.setDelay(Duration.millis(1000));
        timeline4.play();
    }

    private void phase2() {
        timeline7 = new Timeline(new KeyFrame(Duration.millis(getRandomTime()), actionEvent -> {
            TurningRound.setTurnBase(-TurningRound.getTurnBase());
            phase2();
        }));
        timeline7.setCycleCount(1);
        timeline7.play();
    }

    private double getRandomTime() {
        return Math.random() * 5000 + 4000;
    }

    public void rotate(Pane game) {
        pane = game;
        turningRound = new TurningRound(game, AppController.getGameDataBase());
        turningRound.play();
    }

    public void finish() {
        phase2 = false;
        phase3 = false;
        phase4 = false;
        if (timeline3 != null)
            timeline3.stop();
        if (timeline4 != null)
            timeline4.stop();
        if (timeline5 != null)
            timeline5.stop();
        if (timeline7 != null)
            timeline7.stop();
        if (phase2Animation != null)
            phase2Animation.stop();

    }
}
