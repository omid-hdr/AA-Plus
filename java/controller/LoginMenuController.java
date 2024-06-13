package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Database;
import view.LoginMenu;
import view.MainMenu;

import static controller.AppController.showAlert;

public class LoginMenuController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void reset() {
        username.setText("");
        password.setText("");
    }

    public void login() throws Exception {
        Database database = AppController.getDatabase();
        if (database.getUserByUsername(username.getText()) == null)
            showAlert("Login failed", "User not found!");
        else if (password.getText().length() < 4)
            showAlert("Login failed", "password is too short!");
        else if (!database.getUserByUsername(username.getText()).getPassword().equals(password.getText()))
            showAlert("Login Failed", "Incorrect Password!");
        else {
            AppController.setCurrentUser(database.getUserByUsername(username.getText()));
            new MainMenu().start(LoginMenu.stage);
        }
    }

    public void register() throws Exception {
        Database database = AppController.getDatabase();
        if (username.getText().length() == 0)
            showAlert("Register failed", "username is empty");
        else if (database.getUserByUsername(username.getText()) != null)
            showAlert("Register failed", "User exist with this username!");
        else if (password.getText().length() < 4)
            showAlert("Register failed", "password is too short!");
        else {
            database.addUser(username.getText(), password.getText());
            AppController.setCurrentUser(database.getUserByUsername(username.getText()));
            database.saveDataIntoFile();
            new MainMenu().start(LoginMenu.stage);
        }
    }

    public void guest() throws Exception {
        AppController.setCurrentUser(null);
        new MainMenu().start(LoginMenu.stage);
    }
}
