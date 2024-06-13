module aa {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;


    exports view;
    opens view to javafx.fxml, javafx.media;

    exports controller;
    opens controller to javafx.fxml, javafx.media;

    exports model;
    opens model to com.google.gson, javafx.fxml;
}