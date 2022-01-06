package com.example.smproject3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class that is in charge of setting the the scene and running the controller.
 * @author Tommy Cho
 */

public class TuitionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TuitionApplication.class.getResource("tuition-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 775, 800);
        stage.setTitle("Tuition Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
