package sample;
/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

/***/
public class Controller {
    /***/
    @FXML
    private Pane menuArea;
    /***/
    @FXML // fx:id="gameArea"
    private Pane gameArea; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="timerLabel"
    private Label timerLabel; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="startButton"
    private Button startButton; // Value injected by FXMLLoader
    /***/
    @FXML // fx:id="scoreLabel"
    private Label scoreLabel; // Value injected by FXMLLoader
    /***/
    private static int score = 0;
    /***/
    private static Random rand = new Random();
    /***/
    private static final int RANDOM_SPEED = 5;
    /***/
    private static double dx = rand.nextInt(RANDOM_SPEED) + 1;
    /***/
    private static double dy = rand.nextInt(RANDOM_SPEED) + 1;
    /***/
    private static final double INC_DX = 0.1;
    /***/
    private static final double INC_DY = 0.1;
    /***/
    private static final int TIMER_MS = 30000;
    /***/
    private static final int MOVE_DURATION_MS = 10;
    /***/
    private static final int SECOND_MS = 1000;
    /***/
    private static final int CYCLE_COUNT = TIMER_MS / MOVE_DURATION_MS;
    /***/
    private static Circle circle;
    /***/
    private static Timeline tl = new Timeline();
    /***/
    private static Timeline tl2 = new Timeline();
    /***/
    private static final int RADIUS = 30;

    /**
     * Start.
     */
    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert gameArea != null : "fx:id=\"gameArea\" was not injected: check your FXML file 'sample.fxml'.";
        assert timerLabel != null : "fx:id=\"timerLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'sample.fxml'.";
        menuArea.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        gameArea.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
        timerLabel.setText(Integer.toString(TIMER_MS / SECOND_MS));
        startButton.setOnMouseClicked((event) -> {
                    tl2.stop();
                    tl.stop();
                    timerLabel.setText(Integer.toString(TIMER_MS / SECOND_MS));
                    gameArea.getChildren().clear();
                    dx = rand.nextInt(RANDOM_SPEED) + 1;
                    dx = rand.nextInt(RANDOM_SPEED) + 1;
                    score = 0;
                    scoreLabel.setText(Integer.toString(score));
                    circle = new Circle(RADIUS);
                    int width = (int) (gameArea.getPrefWidth() - RADIUS);
                    int height = (int) (gameArea.getPrefHeight() - RADIUS);
                    circle.setCenterX(rand.nextInt(width));
                    circle.setCenterY(rand.nextInt(height));
                    if (circle.getCenterX() < RADIUS) {
                        circle.setCenterX(circle.getCenterX() + RADIUS);
                    }
                    if (circle.getCenterY() < RADIUS) {
                        circle.setCenterY(circle.getCenterY() + RADIUS);
                    }
                    circle.setOnMouseClicked((e) -> {
                        score++;
                        dx += INC_DX;
                        dy += INC_DY;
                        scoreLabel.setText(Integer.toString(score));
                    });
                    gameArea.getChildren().add(circle);
                    tl = new Timeline();
                    tl.setCycleCount(CYCLE_COUNT);
                    KeyFrame kf1 = new KeyFrame(Duration.millis(MOVE_DURATION_MS), new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {

                            if (circle.getCenterY() + RADIUS >= gameArea.getPrefHeight()
                                    || circle.getCenterY() - RADIUS < 0) {
                                dy *= -1;
                            }
                            if (circle.getCenterX() + RADIUS >= gameArea.getPrefWidth()
                                    || circle.getCenterX() - RADIUS < 0) {
                                dx *= -1;
                            }
                            circle.setCenterX(circle.getCenterX() + dx);
                            circle.setCenterY(circle.getCenterY() + dy);
                        }
                    });

                    tl.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            circle.setOnMouseClicked(null);
                        }
                    });
                    tl.getKeyFrames().add(kf1);
                    tl2 = new Timeline();
                    tl2.setCycleCount(TIMER_MS / SECOND_MS);
                    tl2.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                        timerLabel.setText(Integer.toString(Integer.parseInt(timerLabel.getText()) - 1));
                    }));
                    tl.play();
                    tl2.play();
                }

        );
    }
}
