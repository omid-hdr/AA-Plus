package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.LoginMenu;
import view.MainMenu;
import view.ProfileMenu;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static controller.AppController.showAlert;

public class ProfileMenuController {
    @FXML
    private PasswordField newPassword;
    @FXML
    private TextField newUserName;

    public void logOut() throws Exception {
        AppController.setCurrentUser(null);
        new LoginMenu().start(LoginMenu.stage);
    }

    public void back() throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void deleteAccount() throws Exception {
        AppController.getDatabase().removeUser(AppController.getCurrentUser());
        AppController.setCurrentUser(null);
        new LoginMenu().start(LoginMenu.stage);
    }

    public void chooseFile() {
        Stage primaryStage = LoginMenu.stage;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            try {
                URL fileUrl = selectedFile.toURI().toURL();
                AppController.getCurrentUser().setAvatarUrl(fileUrl);
                ProfileMenu.getImageView().setImage(new Image(String.valueOf(AppController.getCurrentUser().getAvatarUrl())));
            } catch (MalformedURLException e) {
                System.out.println("Error converting file to URL: " + e.getMessage());
            }
        }
    }

    public void changeUsername() {
        if (newUserName.getText().length() == 0)
            showAlert("change username failed", "complete new username field first!");
        else if (newUserName.getText().equals(AppController.getCurrentUser().getUsername()))
            showAlert("change username failed", "enter a new username!");
        else {
            AppController.getCurrentUser().setUsername(newUserName.getText());
            AppController.getDatabase().saveDataIntoFile();
        }
        newUserName.setText("");
    }

    public void changePassword() {
        if (newPassword.getText().length() < 4)
            showAlert("change password failed", "password must have at least 4 character!");
        else if (newPassword.getText().equals(AppController.getCurrentUser().getPassword()))
            showAlert("change password failed", "enter a new password!");
        else {
            AppController.getCurrentUser().setPassword(newPassword.getText());
            AppController.getDatabase().saveDataIntoFile();
        }
        newPassword.setText("");
    }

    public void set0() throws IOException {
        AppController.getCurrentUser().setAvatarUrl((ProfileMenu.class.getResource("/image/avatar0.png")));
        AppController.getDatabase().saveDataIntoFile();
        ProfileMenu.getImageView().setImage(new Image(Objects.requireNonNull(ProfileMenu.class.getResource
                ("/image/avatar0.png")).openStream()));
    }

    public void set1() throws IOException {
        AppController.getCurrentUser().setAvatarUrl((ProfileMenu.class.getResource("/image/avatar1.png")));
        AppController.getDatabase().saveDataIntoFile();
        ProfileMenu.getImageView().setImage(new Image(Objects.requireNonNull(ProfileMenu.class.getResource
                ("/image/avatar1.png")).openStream()));
    }

    public void set2() throws IOException {
        AppController.getCurrentUser().setAvatarUrl((ProfileMenu.class.getResource("/image/avatar2.png")));
        AppController.getDatabase().saveDataIntoFile();
        ProfileMenu.getImageView().setImage(new Image(Objects.requireNonNull(ProfileMenu.class.getResource
                ("/image/avatar2.png")).openStream()));
    }


}
