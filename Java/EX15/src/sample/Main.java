package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***/
public class Main extends Application {
    /**
     * @param primaryStage Scene.
     * @throws Exception Stuff.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("EX15: Miinikits Miina");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * @param args Stuff.
     */
    public static void main(String[] args) {
        launch(args);
    }
}