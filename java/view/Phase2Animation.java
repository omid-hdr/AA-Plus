package view;

import controller.AppController;
import javafx.animation.Transition;
import javafx.util.Duration;
import model.Ball;

import java.util.ArrayList;

public class Phase2Animation extends Transition {

    private final ArrayList<Ball> balls;

    public Phase2Animation() {
        this.balls = AppController.getGameDataBase().getBalls();
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        for (Ball ball : balls)
            ball.changeSize();
    }

}
