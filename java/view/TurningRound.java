package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.GameDataBase;

import java.util.ArrayList;

public class TurningRound extends Transition {

    private static double turnBase;
    private final Pane pane;
    private final ArrayList<Ball> balls;

    public TurningRound(Pane pane, GameDataBase gameDataBase) {
        this.pane = pane;
        this.balls = gameDataBase.getBalls();
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
    }

    public static double getTurnBase() {
        return turnBase;
    }

    public static void setTurnBase(double turnBase) {
        TurningRound.turnBase = turnBase;
    }

    @Override
    protected void interpolate(double frac) {
        for (Ball ball : balls) {
            double newDegree = ball.getDegree() + turnBase;
            ball.setDegree(newDegree);
            try {
                ball.checkRotate(pane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
