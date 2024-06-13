package model;

import controller.AppController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Objects;

public class Rocket extends Circle {
    private final int R;
    private final Line line;

    public Rocket() {
        super(450, 550, 40);
        this.setFill(new ImagePattern(
                new Image(Objects.requireNonNull(Rocket.class.getResource("/image/Rocket.jpg")).toExternalForm())));
        this.line = new Line(450, 550, 450, 450);
        this.R = 100;
    }

    public void checkLine() {
        double degree = AppController.getGameDataBase().getDegree();
        line.setStartY(this.getCenterY());
        line.setStartX(this.getCenterX());
        line.setEndX(this.getCenterX() + R * Math.cos(degree));
        if (this.getCenterY() == 20) {
            line.setEndY(this.getCenterY() + R * Math.sin(degree));
        } else
            line.setEndY(this.getCenterY() - R * Math.sin(degree));
    }

    public Line getLine() {
        return line;
    }
}
