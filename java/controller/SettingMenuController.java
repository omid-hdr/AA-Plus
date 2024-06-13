package controller;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import view.LoginMenu;
import view.MainMenu;

import static view.SettingMenu.scene;

public class SettingMenuController {
    public CheckBox black;
    public CheckBox mute;
    public Button shoot1;
    public Button shoot2;
    public Button freeze;
    public Button back1;
    public Button changeLanguage1;
    private KeyCode selectedKey;

    public void mute() {
        AppController.mute(mute.isSelected());
    }

    public void changeLanguage() {
        Language.setIsFarsi(!Language.isIsFarsi());
        back1.setText(Language.BACK.getString());
        black.setText(Language.WHITE_BLACK.getString());
        mute.setText(Language.MUTE.getString());
        shoot1.setText(Language.SHOOT1.getString());
        shoot2.setText(Language.SHOOT2.getString());
        freeze.setText(Language.FREEZE.getString());
        changeLanguage1.setText(Language.CHANGE_LANGUAGE.getString());
    }

    public void whiteAndBlack() {
        AppController.setBlackWhite(black.isSelected());
    }

    public void back() throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void selectShoot1() {
        scene.setOnKeyPressed(event -> {
            selectedKey = event.getCode();
            shoot1.setText("Shoot1 Key = " + selectedKey.getName());
            AppController.setShoot1(selectedKey.getName());
        });
    }

    public void selectShoot2() {
        scene.setOnKeyPressed(event -> {
            selectedKey = event.getCode();
            shoot2.setText("Shoot2 Key = " + selectedKey.getName());
            AppController.setShoot2(selectedKey.getName());
        });
    }

    public void selectFreeze() {
        scene.setOnKeyPressed(event -> {
            selectedKey = event.getCode();
            freeze.setText("Freeze Key = " + selectedKey.getName());
            AppController.setFreeze(selectedKey.getName());
        });
    }
}
