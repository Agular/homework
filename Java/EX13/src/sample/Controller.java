package sample;

/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * THe controller for the game UI.
 */
public class Controller {
    /***/
    public static final int INITIAL_CURSORPRICE = 20;
    /***/
    public static final int INITIAL_CLICKERTIME = 5100;
    /***/
    public static final int INITIAL_CLICKERPRICE = 100;
    /***/
    public static final int MIN_CLICKERTIME = 1000;
    /***/
    public static final int ADD_CURSORPRICE = 20;
    /***/
    public static final int ADD_CLICKERPRICE = 200;
    /***/
    public static final int DEC_CLICKERTIME = 100;
    /***/
    public static int cookieCount = 0;
    /***/
    public static int cursorCount = 0;
    /***/
    public static int clickerCount = 0;
    /***/
    public static int cursorPrice = INITIAL_CURSORPRICE;
    /***/
    public static int clickerPrice = INITIAL_CLICKERPRICE;
    /***/
    public static int clickerTime = INITIAL_CLICKERTIME;
    /***/
    public static Timeline timeline;
    /***/
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    /***/
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    /***/
    @FXML // fx:id="cookie"
    private ImageView cookie; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="cookieLabel"
    private Label cookieLabel; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="cursorButton"
    private Button cursorButton; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="clickerButton"
    private Button clickerButton; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="cursorLabel"
    private Label cursorLabel; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="clickerLabel"
    private Label clickerLabel; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="about"
    private Button about; // Value injected by FXMLLoader

    /***/
    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cookie != null : "fx:id=\"cookie\" was not injected: check your FXML file 'sample.fxml'.";
        assert cookieLabel != null : "fx:id=\"cookieLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert cursorButton != null : "fx:id=\"cursorButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert clickerButton != null : "fx:id=\"clickerButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert cursorLabel != null : "fx:id=\"cursorLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert clickerLabel != null : "fx:id=\"clickerLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert about != null : "fx:id=\"about\" was not injected: check your FXML file 'sample.fxml'.";

        cookie.setOnMouseClicked((event) -> {
            // Button was clicked, do something...
            cookieCount += 1 + cursorCount;
            cookieLabel.setText(Integer.toString(cookieCount));
        });

        cursorButton.setOnMouseClicked((event) -> {
            if (cookieCount >= cursorPrice) {
                cursorCount++;
                cookieCount -= cursorPrice;
                cursorPrice += ADD_CURSORPRICE;
                cursorButton.setText("Cursor \n" + Integer.toString(cursorPrice));
                cursorLabel.setText(Integer.toString(cursorCount));
                cookieLabel.setText(Integer.toString(cookieCount));
            }
        });

        clickerButton.setOnMouseClicked((event) -> {
            if (cookieCount >= clickerPrice && clickerTime > MIN_CLICKERTIME) {
                clickerTime -= DEC_CLICKERTIME;
                cookieCount -= clickerPrice;
                clickerPrice += ADD_CLICKERPRICE;
                clickerCount++;
                cookieLabel.setText(Integer.toString(cookieCount));
                clickerButton.setText("Clicker \n" + Integer.toString(clickerPrice));
                clickerLabel.setText(Integer.toString(clickerCount));
                if (timeline == null) {
                    timeline = new Timeline();
                    KeyFrame kf = new KeyFrame(Duration.millis(clickerTime), event1 -> {
                        cookieCount += cursorCount;
                        cookieLabel.setText(Integer.toString(cookieCount));
                    });
                    timeline.getKeyFrames().add(kf);
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                } else {
                    timeline.stop();
                    timeline = new Timeline();
                    KeyFrame kf = new KeyFrame(Duration.millis(clickerTime), event1 -> {
                        cookieCount += cursorCount;
                        cookieLabel.setText(Integer.toString(cookieCount));
                    });
                    timeline.getKeyFrames().add(kf);
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        });
        about.setOnMouseClicked((event) -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(root1));
            stage.show();

        });
    }
}
