package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * EX11.
 */
public class Main extends Application {
    /***/
    public static final int WINDOW_HEIGHT = 400;
    /***/
    public static final int WINDOW_WIDTH = 600;

    /**
     * Do Stuff.
     *
     * @param primaryStage The main window.
     * @throws Exception Whatever.
     */
    @Override
    public final void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("EX11");
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }

    /**
     * Execute the JavaFX.
     *
     * @param args Stuff.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
