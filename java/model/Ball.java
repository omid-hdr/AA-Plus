package model;

import controller.AppController;
import controller.GameController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import view.Game;

import java.util.Objects;

public class Ball extends Circle {

    public static final double PI = 3.14159265359;
    private static int R = 130;
    private final int CENTER_X = 450;
    private final int CENTER_Y = 250;
    private final Label label;
    private double degree;
    private Line line;
    private boolean onTarget;
    private boolean isGrowing;

    public Ball() {
        super(Game.rocket.getCenterX(), Game.rocket.getCenterY(), 12);
        this.onTarget = false;
        this.degree = -PI / 2;
        this.label = new Label(String.valueOf(AppController.getGameDataBase().getBallsLeft()));
        AppController.getGameDataBase().shoot();
        Image image = new Image(Objects.requireNonNull(Ball.class.getResource("/image/moon.jpg")).toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
        this.isGrowing = true;
        label.setFont(new Font(18));
        label.setTextFill(Color.ALICEBLUE);
    }

    public Ball(Double degree) {
        super(450, 250, 12);
        this.degree = degree;
        this.onTarget = true;
        this.isGrowing = true;
        label = new Label();
    }

    public static int getR() {
        return R;
    }

    public static void setR(int r) {
        R = r;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public boolean isOnTarget() {
        return onTarget;
    }

    public void setOnTarget(boolean onTarget) {
        this.onTarget = onTarget;
    }

    public void changeSize() {
        double radius = this.getRadius();
        int MAX_RADIUS = 13;
        int MIN_RADIUS = 12;
        if (MAX_RADIUS <= radius)
            isGrowing = false;
        else if (MIN_RADIUS >= radius)
            isGrowing = true;
        double changeRadius = -0.01;
        if (isGrowing)
            changeRadius = 0.01;
        this.setRadius(radius + changeRadius);
    }

    private void checkLine(Pane pane) throws Exception {
        if (line == null) {
            line = new Line(CENTER_X, CENTER_Y, CENTER_X + Math.cos(degree), CENTER_Y + Math.sin(degree));
            GameController.intersects();
            pane.getChildren().addAll(line, label);
        } else {
            line.setEndX(this.getCenterX());
            line.setEndY(this.getCenterY());
            int r = 60;
            line.setStartX(CENTER_X - r * Math.cos(degree));
            line.setStartY(CENTER_Y - r * Math.sin(degree));
        }
    }


    public void checkRotate(Pane pane) throws Exception {
        this.setCenterX(CENTER_X - R * Math.cos(degree));
        this.setCenterY(CENTER_Y - R * Math.sin(degree));
        label.setLayoutX(CENTER_X - R * Math.cos(degree) - 13);
        label.setLayoutY(CENTER_Y - R * Math.sin(degree) - 13);
        checkLine(pane);
    }

    public void unVisible() {
        this.setVisible(false);
        line.setVisible(false);
        label.setVisible(false);
    }

    public void visible() {
        this.setVisible(true);
        line.setVisible(true);
        label.setVisible(true);
    }
}
