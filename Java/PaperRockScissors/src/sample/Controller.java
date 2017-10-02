package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Controller {
    /***/
    @FXML
    private ResourceBundle resources;
    /***/
    @FXML
    private URL location;
    /***/
    @FXML
    private ImageView pRock;
    /***/
    @FXML
    private ImageView pPaper;
    /***/
    @FXML
    private ImageView pScissors;
    /***/
    @FXML
    private ImageView iRock;
    /***/
    @FXML
    private ImageView iPaper;
    /***/
    @FXML
    private ImageView iScissors;
    /***/
    @FXML
    private Button best11;
    /***/
    @FXML
    private Button best21;
    /***/
    @FXML
    private Label scoreLabel;
    /***/
    @FXML
    private Label choiceLabel;
    @FXML
    private Label countLabel;
    /***/
    private int noGames;
    /***/
    private static final int SHORT_GAME = 11;
    /***/
    private static final int LONG_GAME = 21;
    /***/
    private int computer;
    /***/
    private int player;
    /***/
    private int computerwins;
    /***/
    private int playerwins;

    /***/
    @FXML
    void initialize() {
        assert pRock != null : "fx:id=\"pRock\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert pPaper != null : "fx:id=\"pPaper\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert pScissors != null : "fx:id=\"pScissors\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert iRock != null : "fx:id=\"iRock\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert iPaper != null : "fx:id=\"iPaper\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert iScissors != null : "fx:id=\"iScissors\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert best11 != null : "fx:id=\"best11\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert best21 != null : "fx:id=\"best21\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert scoreLabel != null : "fx:id=\"scoreLabel\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert choiceLabel != null : "fx:id=\"choiceLabel\" was not injected: check your FXML file 'ChooseGame.fxml'.";
        assert countLabel != null : "fx:id=\"countLabel\" was not injected: check your FXML file 'ChooseGame.fxml'.";
    }

    /***/
    @FXML
    void playShortGame() {
        noGames = SHORT_GAME;
        hideButtons();
        startGame();
    }

    /***/
    @FXML
    void playLongGame() {
        noGames = LONG_GAME;
        hideButtons();
        startGame();
    }

    /***/
    void hideButtons() {
        best11.setVisible(false);
        best21.setVisible(false);
    }

    /**
     * Rock-Paper-Scissor quick winner decider
     * Each move has a index: rock = 0, paper = 1, scissor = 2
     * Return:
     * If 1, computer wins, if 2, player wins.
     * If 0, tie.
     */
    void decideWinner() {
        int winner = (3 + computer - player) % 3;
        switch (winner) {
            case 1:
                computerwins++;
                printGameBoard();
                break;
            case 2:
                playerwins++;
                printGameBoard();
                break;
            case 0:
                break;
        }
    }

    /***/
    void startGame() {
        playerwins = 0;
        computerwins = 0;
        scoreLabel.setVisible(true);
        choiceLabel.setVisible(true);
        printGameBoard();
        setPlayerControls(true);
    }

    /***/
    void printGameBoard() {
        scoreLabel.setText(String.format("%d : %d", computerwins, playerwins));
    }

    /***/
    void decideComputerMove() {
        Random rand = new Random();
        computer = rand.nextInt(2);
    }

    void setPlayerControls(boolean set) {
        if (set) {
            pRock.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    player = 0;
                    setPlayerControls(false);
                    countdown();
                }
            });
            pPaper.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    player = 1;
                    setPlayerControls(false);
                    countdown();
                }
            });
            pScissors.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    player = 2;
                    setPlayerControls(false);
                    countdown();
                }
            });
        } else {
            pRock.setOnMouseClicked(null);
            pPaper.setOnMouseClicked(null);
            pScissors.setOnMouseClicked(null);
        }
    }

    void countdown() {
        countLabel.setVisible(true);
        choiceLabel.setVisible(false);
        Timeline finalCountDown = new Timeline();
        finalCountDown.setCycleCount(3);
        KeyFrame countDown = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!countLabel.getText().equals("1")) {
                    countLabel.setText(Integer.toString(Integer.parseInt(countLabel.getText()) - 1));
                }
            }

        });
        finalCountDown.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                countLabel.setVisible(false);
                countLabel.setText("3");
                setPlayerControls(true);
                decideComputerMove();
                decideWinner();
            }
        });
        finalCountDown.getKeyFrames().addAll(countDown);
        finalCountDown.play();
    }
}
