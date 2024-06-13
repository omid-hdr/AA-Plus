package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class Circle extends javafx.scene.shape.Circle {
    public Circle() {
        super(450, 250, 60);
        this.setFill(new ImagePattern(
                new Image(Objects.requireNonNull(Circle.class.getResource("/image/earth.jpg")).toExternalForm())));
    }
}
