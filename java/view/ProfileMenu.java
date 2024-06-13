package view;

import controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ProfileMenu extends Application {
    private static final ImageView imageView;

    static {
        try {
            imageView = new ImageView(new Image(AppController.getCurrentUser().getAvatarUrl().openStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageView getImageView() {
        return imageView;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = ProfileMenu.class.getResource("/FXML/ProfileMenu.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        imageView.setFitHeight(70);
        imageView.setFitWidth(100);
        pane.getChildren().add(imageView);
        primaryStage.setTitle("ProfileMenu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
