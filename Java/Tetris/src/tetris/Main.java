package tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent mainUI = FXMLLoader.load(getClass().getResource("TetrisMainUI.fxml"));
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(new Scene(mainUI));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
