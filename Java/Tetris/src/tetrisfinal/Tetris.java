package tetrisfinal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Created by Ragnar on 18.04.2016.0
 * v0.01 - followed the tutorial video till the beginng of the 2nd one.
 * v0.02 - added drop() function to drop random tetriminos, need to fix rotate collision,
 * moveleft and moveright collision with tetriminos, add simple score, highscore, settings, combos, make game faster.
 * v0.03 - haven't logged much. First design elements, hold. etc. FIx rot glitch, I rot glitch, created basis
 * to update levels and lines left. Check todo.txt.
 * v0.04 - Made universal collision detection for moving stuff.
 */
public class Tetris extends Application {
    /***/
    public static final int BLOCK_SIZE = 25;
    /***/
    public static final int ROWS = 20;
    /***/
    public static final int COLUMNS = 10;
    /***/
    public static final int HEIGHT = 20 * BLOCK_SIZE;
    /***/
    public static final int WIDTH = 10 * BLOCK_SIZE;
    /***/
    private static final int HOLD_NEXT_HEIGHT = 4 * BLOCK_SIZE;
    /***/
    private static final int HOLD_NEXT_WIDTH = 4 * BLOCK_SIZE;
    /***/
    private static final int INITIAL_DROP_TIME_MS = 550;
    /***/
    private static final int DECDROPTIME = 20;
    /***/
    private static final int LINES_PER_LEVEL = 10;
    /***/
    private static final int MAX_SCORES = 10;
    /***/
    private static final int MAIN_WINDOW_WIDTH = 650;
    /***/
    private static final int MAIN_WINDOW_HEIGHT = 600;
    /***/
    private static final int SETT_HSSCR_WIDTH = 320;
    /***/
    private static final int SETT_HSSCR_HEIGHT = 300;
    /***/
    private static final int DEF_TETRIMINO_X = 4 * BLOCK_SIZE;
    /***/
    private static final int DEF_TETRIMINO_Y = 0;
    /***/
    private static final int COLLISION_RIGHT = 1;
    /***/
    private static final int COLLISION_LEFT = -1;
    /***/
    private static final int COLLISION_TETRIMINO = 2;
    /***/
    private static final int COLLISION_BOTTOM = 3;
    /***/
    private int dropTime;
    /***/
    private boolean dropped;
    /***/
    private boolean gameOver;
    /***/
    private boolean canHold;
    /***/
    private int score;
    /***/
    private ArrayList<Hiscore> hiscores;
    /***/
    private int level;
    /***/
    private int linesleft;
    /***/
    boolean customsettings = false;
    /***/
    int customLevel;
    /***/
    Random rand = new Random();
    /***/
    Pane main;
    /***/
    Timeline dropTimeline = new Timeline();
    /***/
    Tetrimino activeTetrimino;
    /***/
    Tetrimino nextTetrimino;
    /***/
    Tetrimino holdTetrimino;
    /***/
    Pane gameArea;
    /***/
    Pane nextArea;
    /***/
    Pane holdArea;
    /***/
    Label scoreLabel;
    /***/
    Label linesValue;
    /***/
    Label levelValue;
    /***/
    Button settingsButton;
    /***/
    Button playButton;
    /***/
    Button highscoreButton;
    /***/
    Button quitButton;

    /**
     * Start the main pane.
     *
     * @param primaryStage The main Stage.
     * @throws Exception If hiscore file is not found.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        main = new Pane();
        main.setId("pane");
        Scene scene = new Scene(main);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setWidth(MAIN_WINDOW_WIDTH);
        primaryStage.setHeight(MAIN_WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.show();
        createGameUI();
        InputStream in = getClass().getClassLoader().getResourceAsStream("tetrisfinal/hiscore.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        hiscores = new ArrayList<Hiscore>();
        while ((line = br.readLine()) != null) {
            String[] info = line.split(" ");
            Hiscore hiscore = new Hiscore(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
            hiscores.add(hiscore);
        }
        Collections.sort(hiscores);
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startNewGame();
            }
        });
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });
        settingsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dropTimeline.pause();
                Stage stage = new Stage();
                stage.setTitle("SETTINGS");
                Pane settingPane = new Pane();
                stage.setScene(new Scene(settingPane, SETT_HSSCR_WIDTH, SETT_HSSCR_HEIGHT));
                Slider slider = new Slider();
                final int sliderX = 50;
                final int sliderY = 100;
                final int sliderWidth = 200;
                final int sliderMax = 20;
                final int sliderMajorTick = 5;
                slider.setPrefWidth(sliderWidth);
                slider.setLayoutX(sliderX);
                slider.setLayoutY(sliderY);
                slider.setMin(0);
                slider.setMax(sliderMax);
                slider.setValue(0);
                slider.setShowTickMarks(true);
                slider.setShowTickLabels(true);
                slider.setMajorTickUnit(sliderMajorTick);
                slider.snapToTicksProperty().set(true);
                settingPane.getChildren().add(slider);
                slider.valueProperty().addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        customLevel = (int) slider.getValue();
                        customsettings = true;
                    }
                });
                stage.show();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        dropTimeline.play();
                    }
                });
            }
        });
        highscoreButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ObservableList<Hiscore> hiscoreData = FXCollections.observableArrayList();
                hiscoreData.addAll(hiscores);
                Stage stage = new Stage();
                stage.setTitle("Hiscores");
                Pane hiscorePane = new Pane();
                TableView<Hiscore> table = new TableView<Hiscore>();
                table.setItems(hiscoreData);
                TableColumn<Hiscore, String> nameColumn = new TableColumn<Hiscore, String>("Nickname");
                TableColumn<Hiscore, Integer> levelColumn = new TableColumn<Hiscore, Integer>("Level");
                TableColumn<Hiscore, Integer> scoreColumn = new TableColumn<Hiscore, Integer>("Score");
                nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
                levelColumn.setCellValueFactory(cellData -> cellData.getValue().getLevelProperty().asObject());
                scoreColumn.setCellValueFactory(cellData -> cellData.getValue().getScoreProperty().asObject());
                System.out.println("hiscore");
                table.getColumns().addAll(nameColumn, levelColumn, scoreColumn);
                hiscorePane.getChildren().add(table);
                stage.setScene(new Scene(hiscorePane, SETT_HSSCR_WIDTH, SETT_HSSCR_HEIGHT));
                stage.show();


            }
        });
    }

    /**
     * Move the Tetrimino down.
     * The function that will be used in the Timeline.
     */
    private void drop() {
        if (activeTetrimino == null) {
            if (nextTetrimino == null) {
                char nt = Tetrimino.SHAPES[rand.nextInt(Tetrimino.SHAPES.length)];
                nextTetrimino = new Tetrimino(nt, DEF_TETRIMINO_X, DEF_TETRIMINO_Y);
            }
            dropped = false;
            char nt = Tetrimino.SHAPES[rand.nextInt(Tetrimino.SHAPES.length)];
            nextArea.getChildren().remove(nextTetrimino);
            activeTetrimino = nextTetrimino;
            activeTetrimino.setX(DEF_TETRIMINO_X);
            activeTetrimino.setY(DEF_TETRIMINO_Y);
            nextTetrimino = new Tetrimino(nt, 0, BLOCK_SIZE);
            nextArea.getChildren().add(nextTetrimino);
            gameArea.getChildren().add(activeTetrimino);
            if (collisionDetection(activeTetrimino) != 0) {
                dropped = true;
                dropTimeline.stop();
                gameOver = true;
                gameArea.getChildren().remove(activeTetrimino);
                activeTetrimino = null;
                checkHiscore();
                return;
            }
            return;
        }
        drop(activeTetrimino);
        if (dropped) {
            activeTetrimino = null;
            ArrayList<Integer> compLines = detectLines();
            clearLines(compLines);
            addPoints(compLines.size());
            upgradeLevel(compLines.size());
            canHold = true;
        }
    }

    /**
     * Move the Tetrimino down.
     *
     * @param t The tetrimino to be moved.
     */
    public void drop(Tetrimino t) {
        if (t == null || dropTimeline.getStatus() == Animation.Status.PAUSED) return;
        // liiguta allapoole, kuni saab
        if (t == null) return;
        t.addY(BLOCK_SIZE);
        if (collisionDetection(t) != 0) {
            t.addY(-BLOCK_SIZE);
            dropped = true;
        }
    }

    /**
     * Move the Tetrimino right.
     *
     * @param t The tetrimino to be moved.
     */
    public void moveRight(Tetrimino t) {
        if (t == null || dropTimeline.getStatus() == Animation.Status.PAUSED) return;
        t.addX(BLOCK_SIZE);
        while (collisionDetection(t) != 0) {
            t.addX(-BLOCK_SIZE);
        }
    }

    /**
     * Move the Tetrimino left.
     *
     * @param t The tetrimino to be moved.
     */
    public void moveLeft(Tetrimino t) {
        if (t == null || dropTimeline.getStatus() == Animation.Status.PAUSED) return;
        t.addX(-BLOCK_SIZE);
        while (collisionDetection(t) != 0) {
            t.addX(BLOCK_SIZE);
        }
    }

    /**
     * Rotate the Tetrimino and detect collisions.
     *
     * @param t The Tetrimino to be rotated.
     */
    public void rotate(Tetrimino t) {
        if (t == null || dropTimeline.getStatus() == Animation.Status.PAUSED) return;
        t.rotate(1);
        int collisionMessage = collisionDetection(t);
        while (collisionMessage != 0) {
            if (collisionMessage == COLLISION_RIGHT) {
                t.addX(-BLOCK_SIZE);
            } else if (collisionMessage == COLLISION_LEFT) {
                t.addX(BLOCK_SIZE);
            } else if (collisionMessage == COLLISION_BOTTOM) {
                t.addY(-BLOCK_SIZE);
            } else if (collisionMessage == COLLISION_TETRIMINO) {
                t.rotate(-1);
            }
            collisionMessage = collisionDetection(t);
        }
    }

    /**
     * Detect the lines that are complete.
     *
     * @return The list of completed line indexes starting from the highest line.
     */
    public ArrayList<Integer> detectLines() {
        int[] lines = new int[ROWS];
        ArrayList<Integer> completeLines = new ArrayList<>();
        for (Node n : gameArea.getChildren()) {
            if (!(n instanceof Tetrimino)) continue;
            Tetrimino t = (Tetrimino) n;
            for (Node nn : t.getChildren()) {
                if (!(nn instanceof Rectangle)) continue;
                Rectangle r = (Rectangle) nn;
                int idx = (int) r.getY() / BLOCK_SIZE - 1;
                lines[idx]++;
                if (lines[idx] == COLUMNS) {
                    completeLines.add(idx);
                }
            }
        }
        return completeLines;
    }

    /**
     * Clear lines and drop down other lines above them.
     *
     * @param completed The list containing the indexes of lines completed starting from the highest.
     */
    public void clearLines(ArrayList<Integer> completed) {
        for (int row : completed) {
            for (Node n : gameArea.getChildren()) {
                if (!(n instanceof Tetrimino)) continue;
                Tetrimino t = (Tetrimino) n;
                ArrayList<Rectangle> deletables = new ArrayList();
                for (Node nn : t.getChildren()) {
                    if (!(nn instanceof Rectangle)) continue;
                    Rectangle r = (Rectangle) nn;
                    int idx = (int) r.getY() / BLOCK_SIZE - 1;
                    if (idx == row) {
                        deletables.add(r);
                    }
                    if (idx < row) {
                        r.setY(r.getY() + BLOCK_SIZE);
                    }
                }
                for (Rectangle deletable : deletables) {
                    t.remove(deletable);
                }
            }
        }
    }

    /**
     * Add points for clearing lines.
     * Maybe needs some enhancement.
     *
     * @param lines THe amount of lines cleared.
     */
    public void addPoints(int lines) {
        final int score1 = 100;
        final int score2 = 200;
        final int score3 = 900;
        final int score4 = 1600;
        if (lines == 1) {
            score += score1;
        } else if (lines == 2) {
            score += score2;
        } else if (lines == 2 + 1) {
            score += score3;
        } else if (lines == 2 + 2) {
            score += score4;
        }
        updateScore();
    }

    /**
     * Update score counter.
     */
    public void updateScore() {
        scoreLabel.setText(Integer.toString(score));
    }

    /**
     * Update the level counter.
     */
    public void updateLevel() {
        levelValue.setText(Integer.toString(level));
    }

    /**
     * Update the lines left counter.
     */
    public void updateLinesLeft() {
        linesValue.setText(Integer.toString(linesleft));
    }

    /**
     * Put the Tetrimino on hold.
     */
    public void holdTetrimino() {
        if (canHold) {
            if (holdTetrimino == null) {
                holdTetrimino = new Tetrimino(activeTetrimino.getShape(), 0, 0);
                holdTetrimino.setX(0);
                holdTetrimino.setY(2 * BLOCK_SIZE);
                holdArea.getChildren().add(holdTetrimino);
                gameArea.getChildren().remove(activeTetrimino);
                activeTetrimino = null;
                canHold = false;

            } else {
                holdArea.getChildren().remove(holdTetrimino);
                gameArea.getChildren().remove(activeTetrimino);
                Tetrimino temp = new Tetrimino(activeTetrimino.getShape(), 0, 0);
                activeTetrimino = holdTetrimino;
                holdTetrimino = temp;
                gameArea.getChildren().add(activeTetrimino);
                holdArea.getChildren().add(holdTetrimino);
                holdTetrimino.setX(0);
                holdTetrimino.setY(2 * BLOCK_SIZE);
                activeTetrimino.setX(DEF_TETRIMINO_X);
                activeTetrimino.setY(DEF_TETRIMINO_Y);
                canHold = false;
            }
        }
    }

    /**
     * Upgrade the game to be faster.
     *
     * @param clearedLines The amount of lines cleared.
     */
    public void upgradeLevel(int clearedLines) {
        linesleft -= clearedLines;
        if (linesleft > 0) {
            updateLinesLeft();
        } else {
            level++;
            linesleft = LINES_PER_LEVEL;
            updateLinesLeft();
            updateLevel();
            dropTimeline.stop();
            dropTime = INITIAL_DROP_TIME_MS - level * DECDROPTIME;
            dropTimeline = new Timeline();
            dropTimeline.setCycleCount(Timeline.INDEFINITE);
            dropTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(dropTime), event1 -> drop()));
            dropTimeline.play();

        }
    }

    /**
     * Check if new hiscore was made by the players.
     * Asks for name if true and writes it into file.
     */
    public void checkHiscore() {
        boolean add = false;
        if (hiscores.size() < MAX_SCORES) {
            add = true;
        } else {
            Hiscore last = hiscores.get(MAX_SCORES - 1);
            if (score > last.score) {
                add = true;
            }
        }
        if (add) {
            Stage stage = new Stage();
            stage.setTitle("New Hiscore!");
            Pane hiscorePane = new Pane();
            TextField player = new TextField();
            final int playerX = 75;
            final int playerY = 100;
            player.setLayoutY(playerY);
            player.setLayoutX(playerX);
            Button addHiscore = new Button();
            addHiscore.setText("OK");
            final int hiscoreButtonX = 150;
            final int hiscoreButtonY = 150;
            addHiscore.setLayoutX(hiscoreButtonX);
            addHiscore.setLayoutY(hiscoreButtonY);
            hiscorePane.getChildren().add(player);
            hiscorePane.getChildren().add(addHiscore);
            stage.setScene(new Scene(hiscorePane, SETT_HSSCR_WIDTH, SETT_HSSCR_HEIGHT));
            stage.show();
            addHiscore.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (player.getText() != null) {
                        Hiscore newScore = new Hiscore(player.getText(), level, score);
                        hiscores.add(newScore);
                        Collections.sort(hiscores);
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter("src/tetrisfinal/hiscore.txt"));
                            for (int i = 0; i < hiscores.size(); i++) {
                                if (i < MAX_SCORES) {
                                    Hiscore score = hiscores.get(i);
                                    bw.write(score.name + " " + score.level + " " + score.score + "\n");
                                }
                            }
                            bw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage.close();
                    }
                }
            });
        }
    }

    /**
     * Universal collision detection for the game.
     * Did it because some glitches could not be fixed without this.
     *
     * @param t The Tetrimino to be checked.
     * @return 0  if no collision
     * 1  if collision on the right side
     * -1  if collision on the left side
     * 2  if collision with another tetrimino
     * 3  if collison with bottom
     */
    public int collisionDetection(Tetrimino t) {
        for (Node n : t.getChildren()) {
            if (!(n instanceof Rectangle)) continue;
            Rectangle r = (Rectangle) n;
            if (r.getX() >= gameArea.getWidth()) {
                return COLLISION_RIGHT;
            } else if (r.getX() < 0) {
                return COLLISION_LEFT;
            } else if (r.getY() > (ROWS - 1) * BLOCK_SIZE) {
                return COLLISION_BOTTOM;
            }
            for (Node nn : gameArea.getChildren()) {
                if (!(nn instanceof Tetrimino)) continue;
                Tetrimino t2 = (Tetrimino) nn;
                if (t2 == t) continue;
                for (Node nnn : t2.getChildren()) {
                    if (!(nnn instanceof Rectangle)) continue;
                    Rectangle rr = (Rectangle) nnn;
                    if (rr.getX() == r.getX() && rr.getY() == r.getY()) {
                        return COLLISION_BOTTOM;
                    }
                }
            }
        }
        return 0;
    }

    /***/
    public void createGameUI() {
        final int gameAreaX = 200;
        final int gameAreaY = 50;
        gameArea = new Pane();
        gameArea.setLayoutX(gameAreaX);
        gameArea.setLayoutY(gameAreaY);
        gameArea.setPrefWidth(WIDTH);
        gameArea.setPrefHeight(HEIGHT);
        gameArea.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        final int nextAreaX = 475;
        final int nextAreaY = 50;
        nextArea = new Pane();
        nextArea.setLayoutX(nextAreaX);
        nextArea.setLayoutY(nextAreaY);
        nextArea.setPrefWidth(HOLD_NEXT_WIDTH);
        nextArea.setPrefHeight(HOLD_NEXT_HEIGHT);
        nextArea.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        final int holdAreaX = 50;
        final int holdAreaY = 50;
        holdArea = new Pane();
        holdArea.setLayoutX(holdAreaX);
        holdArea.setLayoutY(holdAreaY);
        holdArea.setPrefWidth(HOLD_NEXT_WIDTH);
        holdArea.setPrefHeight(HOLD_NEXT_HEIGHT);
        holdArea.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        final int scoreLabelX = 200;
        final int scoreLabelY = 10;
        scoreLabel = new Label();
        scoreLabel.setLayoutX(scoreLabelX);
        scoreLabel.setLayoutY(scoreLabelY);
        scoreLabel.setId("score");

        final int settingsButtonX = 475;
        final int settingsButtonY = 355;
        settingsButton = new Button();
        settingsButton.setText("SETTINGS");
        settingsButton.setLayoutX(settingsButtonX);
        settingsButton.setLayoutY(settingsButtonY);

        final int playButtonX = 475;
        final int playButtonY = 285;
        playButton = new Button();
        playButton.setText("    PLAY    ");
        playButton.setLayoutX(playButtonX);
        playButton.setLayoutY(playButtonY);

        final int scoreButtonX = 475;
        final int scoreButtonY = 425;
        highscoreButton = new Button();
        highscoreButton.setText("HISCORES");
        highscoreButton.setLayoutX(scoreButtonX);
        highscoreButton.setLayoutY(scoreButtonY);

        final int quitX = 475;
        final int quitY = 495;
        quitButton = new Button();
        quitButton.setText("     QUIT    ");
        quitButton.setLayoutX(quitX);
        quitButton.setLayoutY(quitY);

        final int tetrisX = 495;
        final int tetrisY = 225;
        Label tetris = new Label();
        tetris.setLayoutX(tetrisX);
        tetris.setLayoutY(tetrisY);
        tetris.setText("TETRIS");

        final int nextX = 485;
        final int nextY = 10;
        Label nextLabel = new Label();
        nextLabel.setLayoutY(nextY);
        nextLabel.setLayoutX(nextX);
        nextLabel.setText("NEXT");

        final int holdX = 55;
        final int holdY = 10;
        Label holdLabel = new Label();
        holdLabel.setLayoutY(holdY);
        holdLabel.setLayoutX(holdX);
        holdLabel.setText("HOLD");

        final int levelLabelX = 55;
        final int levelLabelY = 225;
        Label levelLabel = new Label();
        levelLabel.setLayoutX(levelLabelX);
        levelLabel.setLayoutY(levelLabelY);
        levelLabel.setText("LEVEL");

        final int linesX = 55;
        final int linesY = 325;
        Label linesLabel = new Label();
        linesLabel.setLayoutX(linesX);
        linesLabel.setLayoutY(linesY);
        linesLabel.setText("LINES");

        final int levelValX = 55;
        final int levelValY = 275;
        levelValue = new Label();
        levelValue.setLayoutX(levelValX);
        levelValue.setLayoutY(levelValY);

        final int linesValX = 55;
        final int linesValY = 375;
        linesValue = new Label();
        linesValue.setLayoutX(linesValX);
        linesValue.setLayoutY(linesValY);

        main.getChildren().add(gameArea);
        main.getChildren().add(nextArea);
        main.getChildren().add(holdArea);
        main.getChildren().add(scoreLabel);
        main.getChildren().add(playButton);
        main.getChildren().add(settingsButton);
        main.getChildren().add(highscoreButton);
        main.getChildren().add(quitButton);
        main.getChildren().add(tetris);
        main.getChildren().add(levelLabel);
        main.getChildren().add(linesLabel);
        main.getChildren().add(holdLabel);
        main.getChildren().add(nextLabel);
        main.getChildren().add(linesValue);
        main.getChildren().add(levelValue);
    }

    /***/
    public void startNewGame() {
        dropTimeline.stop();
        gameArea.getChildren().clear();
        holdArea.getChildren().clear();
        nextArea.getChildren().clear();
        activeTetrimino = null;
        holdTetrimino = null;
        gameOver = false;
        canHold = true;
        score = 0;
        level = 1;
        if (customsettings) {
            level = customLevel;
        }
        linesleft = LINES_PER_LEVEL;
        updateScore();
        updateLevel();
        updateLinesLeft();
        dropTime = INITIAL_DROP_TIME_MS;
        dropTimeline = new Timeline();
        dropTimeline.setCycleCount(Timeline.INDEFINITE);
        dropTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(dropTime - level * DECDROPTIME),
                event1 -> drop()));
        dropTimeline.play();
        playButton.setFocusTraversable(false);
        settingsButton.setFocusTraversable(false);
        highscoreButton.setFocusTraversable(false);
        quitButton.setFocusTraversable(false);
        gameArea.requestFocus();
        gameArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN:
                        drop(activeTetrimino);
                        break;
                    case LEFT:
                        moveLeft(activeTetrimino);
                        break;
                    case RIGHT:
                        moveRight(activeTetrimino);
                        break;
                    case UP:
                        rotate(activeTetrimino);
                        break;
                    case SPACE:
                        while (!dropped && dropTimeline.getStatus()== Animation.Status.RUNNING) {
                            drop();
                        }
                        break;
                    case SHIFT:
                        holdTetrimino();
                        break;
                    case P:
                        if (dropTimeline.getStatus() == Animation.Status.PAUSED) {
                            dropTimeline.play();
                        } else {
                            dropTimeline.pause();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * Start up the game!
     *
     * @param args The commands from outside though I will have none.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
