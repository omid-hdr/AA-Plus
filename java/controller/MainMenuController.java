package controller;

import view.*;

import static controller.AppController.showAlert;

public class MainMenuController {

    public void newGame() throws Exception {
        AppController.setPlayer2(false);
        new StartGame().start(LoginMenu.stage);
    }

    public void setting() throws Exception {
        new SettingMenu().start(LoginMenu.stage);
    }

    public void profileMenu() throws Exception {
        if (AppController.getCurrentUser() == null)
            showAlert("Login failed", "Login first!");
        else
            new ProfileMenu().start(LoginMenu.stage);
    }

    public void scoreBoard() throws Exception {
        new ScoreBoard().start(LoginMenu.stage);
    }

    public void exit() {
        System.exit(0);
    }

    public void Player2() throws Exception {
        AppController.setPlayer2(true);
        new StartGame().start(LoginMenu.stage);
    }
}
