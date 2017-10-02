package tetris;
/**
 * Sample Skeleton for 'TetrisMainUI.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/***/
public class MainUIController {
    /***/
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    /***/
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    /***/
    @FXML // fx:id="menu"
    private AnchorPane menu; //Value injected by FXMLLoader
    /***/
    @FXML // fx:id="play"
    private Button play; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="settings"
    private Button settings; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="mainUI"
    private SplitPane mainUI; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="highscore"
    private Button highscore; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="gameAreaUI"
    private Board gameAreaUI;

    /***/
    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        play.setOnMouseClicked(event -> {
            gameAreaUI.addRectangle();
        });
    }
}
