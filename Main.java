package Project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//project 3 example can be found in lecture 10 1:11:00

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Tuition Manager");
        primaryStage.setScene(new Scene(root, 775, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
