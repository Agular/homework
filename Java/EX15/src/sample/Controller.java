package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/***/
public class Controller {
    /***/
    @FXML
    private Pane gameArea;
    /***/
    @FXML
    private AnchorPane menuArea;
    /***/
    @FXML
    private Button startButton;
    /***/
    @FXML
    private Label timerLabel;
    /***/
    @FXML
    private Label scoreLabel;
    /***/
    private ImageView goat;
    /***/
    private int score;
    /***/
    private ArrayList<ImageView> bombs;
    /***/
    private Timeline gameTimeline = new Timeline();
    /***/
    private Timeline timer = new Timeline();
    /***/
    private Random random = new Random();
    /***/
    private static final int GAME_TIME_S = 30;
    /***/
    private static final int SPAWN_TIME = 1;
    /***/
    private static final int STEP = 5;
    /***/
    private static final int BOMB_SIZE = 30;
    /***/
    private static final int GOAT_WIDTH = 100;
    /***/
    private static final int GOAT_HEIGHT = 100;

    /***/
    @FXML
    void initialize() {
        assert gameArea != null : "fx:id=\"gameArea\" was not injected: check your FXML file 'sample.fxml'.";
        assert menuArea != null : "fx:id=\"menuArea\" was not injected: check your FXML file 'sample.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert timerLabel != null : "fx:id=\"timerLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert scoreLabel != null : "fx:id=\"scoreLabel\" was not injected: check your FXML file 'sample.fxml'.";
        timerLabel.setText(Integer.toString(GAME_TIME_S));
        score = 0;
        scoreLabel.setText(Integer.toString(score));
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                timer.stop();
                gameTimeline.stop();
                bombs = new ArrayList<ImageView>();
                gameArea.getChildren().clear();
                score = 0;
                timerLabel.setText(Integer.toString(GAME_TIME_S));
                scoreLabel.setText(Integer.toString(score));
                startButton.setFocusTraversable(false);
                goat = new ImageView();
                goat.setImage(new Image("sample/goat-right.png"));
                goat.setFitWidth(GOAT_WIDTH);
                goat.setFitHeight(GOAT_HEIGHT);
                goat.setX(random.nextInt((int) (gameArea.getPrefWidth() - goat.getFitWidth())));
                goat.setY(random.nextInt((int) (gameArea.getPrefHeight() - goat.getFitHeight())));
                gameArea.getChildren().add(goat);
                gameTimeline = new Timeline();
                gameTimeline.setCycleCount(Timeline.INDEFINITE);
                KeyFrame addBombs = new KeyFrame(Duration.seconds(SPAWN_TIME), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ImageView bomb = new ImageView(new Image("sample/bomb.png"));
                        gameArea.getChildren().add(bomb);
                        bomb.setFitWidth(BOMB_SIZE);
                        bomb.setFitHeight(BOMB_SIZE);
                        bomb.setX(random.nextInt((int) (gameArea.getPrefWidth() - bomb.getFitWidth())));
                        bomb.setY(random.nextInt((int) (gameArea.getPrefHeight() - bomb.getFitHeight())));
                        bombs.add(bomb);
                    }
                });
                timer = new Timeline();
                timer.setCycleCount(GAME_TIME_S);
                timer.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        gameTimeline.stop();
                        gameArea.setOnKeyPressed(null);
                    }
                });
                KeyFrame tickTimer = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        timerLabel.setText(Integer.toString(Integer.parseInt(timerLabel.getText()) - 1));
                    }
                });
                gameTimeline.getKeyFrames().add(addBombs);
                timer.getKeyFrames().add(tickTimer);
                gameTimeline.play();
                timer.play();
                gameArea.requestFocus();
                gameArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        switch (event.getCode()) {
                            case DOWN:
                                if (goat.getY() + goat.getFitHeight() < gameArea.getPrefHeight()) {
                                    goat.setY(goat.getY() + STEP);
                                    break;
                                } else {
                                    break;
                                }
                            case LEFT:
                                if (goat.getX() > 0) {
                                    goat.setScaleX(-1);
                                    goat.setX(goat.getX() - STEP);
                                    break;
                                } else {
                                    break;
                                }
                            case RIGHT:
                                if (goat.getX() + goat.getFitWidth() < gameArea.getPrefWidth()) {
                                    goat.setScaleX(1);
                                    goat.setX(goat.getX() + STEP);
                                    break;
                                } else {
                                    break;
                                }
                            case UP:
                                if (goat.getY() > 0) {
                                    goat.setY(goat.getY() - STEP);
                                    break;
                                } else {
                                    break;
                                }
                            default:
                                break;
                        }
                        Iterator<ImageView> iter = bombs.iterator();
                        while (iter.hasNext()) {
                            ImageView bomb = iter.next();
                            if (goat.intersects(bomb.getLayoutBounds())) {
                                score++;
                                scoreLabel.setText(Integer.toString(score));
                                gameArea.getChildren().remove(bomb);
                                iter.remove();
                            }
                        }
                    }
                });
            }
        });
    }
}
