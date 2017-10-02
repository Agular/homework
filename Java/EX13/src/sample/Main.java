package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * THe cookie clicker game.
 */
public class Main extends Application {
    /***/
    private final int width = 400;
    /***/
    private final int height = 500;

    /**
     * Start the app.
     *
     * @param primaryStage The main window.
     * @throws Exception Dunno.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("EX13: Cookie Clicker");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Launch the game.
     *
     * @param args Dunno.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
