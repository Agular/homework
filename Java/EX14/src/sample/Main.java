package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***/
public class Main extends Application {
    /**
     * Set the settings of the scene.
     *
     * @param primaryStage The main scene.
     * @throws Exception Problems.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("EX14");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Launch the window.
     *
     * @param args Do.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
