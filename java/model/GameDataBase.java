package model;

import java.util.ArrayList;

public class GameDataBase {
    private final ArrayList<Ball> balls;
    private int level;
    private int allBalls;
    private int ballsLeft;
    private double degree = Ball.PI / 2;
    private boolean Player2Shoot;

    public GameDataBase(int level, int allBalls, ArrayList<Ball> balls, int map) {
        this.level = level;
        this.allBalls = allBalls;
        this.balls = balls;
        this.ballsLeft = allBalls;
        handleMap(map);
    }

    private void handleMap(int map) {
        for (int i = 0; i < map + 3; i++) {
            double grad = 2 * i * (Ball.PI / ((double) map + 3));
            Ball ball = new Ball(grad);
            ball.setCenterX(450 - 130 * Math.cos(grad));
            ball.setCenterY(250 - 130 * Math.sin(grad));
            balls.add(ball);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAllBalls() {
        return allBalls;
    }

    public void setAllBalls(int allBalls) {
        this.allBalls = allBalls;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public int getFreezeTime() {
        return (9 - level * 2) * 1000;
    }

    public double getSpeed() {
        return ((double) level) / 100;
    }

    public int getBallsLeft() {
        return ballsLeft;
    }

    public void setBallsLeft(int ballsLeft) {
        this.ballsLeft = ballsLeft;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public void shoot() {
        ballsLeft--;
    }

    public boolean isPlayer2Shoot() {
        return Player2Shoot;
    }

    public void setPlayer2Shoot(boolean player2Shoot) {
        Player2Shoot = player2Shoot;
    }
}
