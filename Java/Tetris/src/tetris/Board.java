package tetris;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Board extends Pane {
    /**
     * The height of the Tetris gameboard.
     * Default is 20 bloacks.
     */
    private static final int HEIGHT_IN_BLOCKS = 20;
    /**
     * The width of the Tetris gameboard.
     * Default is 10 blocks.
     */
    private static final int WIDTH_IN_BLOCKS = 10;
    /**
     * The size of the side of the square in pixels.
     */
    private static final int SQUARE_SIZE = 20;

    /***/
    public Board() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Board.fxml"));

        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /***/
    public void addRectangle() {
        Rectangle rec = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        rec.setFill(Color.CYAN);
        rec.setX(280);
        rec.setY(300);
        rec.setStroke(Color.BLACK);
        getChildren().add(rec);
    }
}
