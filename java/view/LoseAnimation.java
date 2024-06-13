package view;

import controller.AppController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;

import java.util.ArrayList;

public class LoseAnimation extends Transition {
    private final Pane pane;
    private final ArrayList<Ball> balls;

    public LoseAnimation(Pane pane) {
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(4000));
        this.balls = AppController.getGameDataBase().getBalls();
    }

    @Override
    protected void interpolate(double frac) {
        for (Ball ball : balls) {
            if (ball.isOnTarget()) {
                double newDegree = ball.getDegree() + TurningRound.getTurnBase();
                ball.setDegree(newDegree);
                try {
                    ball.checkRotate(pane);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (ball.getCenterY() > 375) {
                    ball.setOnTarget(false);
                    ball.setDegree(-Ball.PI / 2);
                    try {
                        ball.checkRotate(pane);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
