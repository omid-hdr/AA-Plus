package view;

import controller.AppController;
import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;

public class ShootingAnimation extends Transition {

    private final Pane pane;
    private final Ball ball;

    public ShootingAnimation(Pane pane, Ball ball) {
        this.pane = pane;
        this.ball = ball;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int SPEED = 8;
        double y = ball.getCenterY() - SPEED * Math.sin(AppController.getGameDataBase().getDegree());
        double x = ball.getCenterX() + SPEED * Math.cos(AppController.getGameDataBase().getDegree());
        if (y < 30 || x < 30 || x > 870) {
            this.stop();
            Game.lose(pane);
        }
        if ((Math.pow(x - 450, 2) +
                Math.pow(y - 250, 2)) < Math.pow(ball.getRadius() + Ball.getR(), 2)) {
            ball.setOnTarget(true);
            ball.setDegree(-Ball.PI + Math.acos((ball.getCenterX() - 450) / (ball.getRadius() + Ball.getR())));
            AppController.getGameDataBase().getBalls().add(ball);
            this.stop();
            GameController.successfulShoot();
        } else
            ball.setCenterY(y);
        ball.setCenterX(x);
    }
}
