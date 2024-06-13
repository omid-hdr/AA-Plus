package view;

import controller.AppController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;

public class WinAnimation extends Transition {

    private final Pane pane;

    public WinAnimation(Pane pane) {
        this.pane = pane;
        this.setCycleDuration(Duration.INDEFINITE);
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        Ball.setR(Ball.getR() + 5);
        if (Ball.getR() > 390)
            this.stop();
        for (Ball ball : AppController.getGameDataBase().getBalls()) {
            try {
                ball.checkRotate(pane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
