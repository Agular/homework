package tetrisfinal;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Ragnar on 18.04.2016.
 * v0.01 - created the basics with the tutorial videos, modified the rotation function,
 * added template shape pattern for other shapes, need to complete them.
 */
public class Tetrimino extends Group {
    /***/
    private int blockSize = Tetris.BLOCK_SIZE;
    /***/
    private static final int MATRIX_ROWS = 4;
    /***/
    private static final int MATRIX_COLUMNS = 4;
    /***/
    private char shape;
    /***/
    private int rotation;
    /***/
    private double x;
    /***/
    private double y;
    /***/
    private Color color;
    /***/
    private static final int MAX_ROTATIONS = 4;
    /**
     * Matrix of the tetrimino.
     */
    private int[][] matrix = new int[MATRIX_ROWS][MATRIX_COLUMNS];
    /***/
    private static final int[] SHAPE_I_0 = {0, 0, 0, 1, 0, 2, 0, 3};
    /***/
    private static final int[] SHAPE_I_1 = {0, 1, 1, 1, 2, 1, 3, 1};
    /***/
    private static final int[] SHAPE_I_2 = {0, 0, 1, 0, 2, 0, 3, 0};
    /***/
    private static final int[] SHAPE_0 = {0, 0, 0, 1, 1, 0, 1, 1};
    /***/
    private static final int[] SHAPE_L_0 = {0, 2, 1, 0, 1, 1, 1, 2};
    /***/
    private static final int[] SHAPE_L_1 = {0, 1, 1, 1, 2, 1, 2, 2};
    /***/
    private static final int[] SHAPE_L_2 = {0, 0, 0, 1, 0, 2, 1, 0};
    /***/
    private static final int[] SHAPE_L_3 = {0, 0, 0, 1, 1, 1, 2, 1};
    /***/
    private static final int[] SHAPE_J_0 = {0, 0, 1, 0, 1, 1, 1, 2};
    /***/
    private static final int[] SHAPE_J_1 = {0, 0, 0, 1, 1, 0, 2, 0};
    /***/
    private static final int[] SHAPE_J_2 = {0, 0, 0, 1, 0, 2, 1, 2};
    /***/
    private static final int[] SHAPE_J_3 = {0, 1, 1, 1, 2, 0, 2, 1};
    /***/
    private static final int[] SHAPE_S_0 = {0, 1, 0, 2, 1, 0, 1, 1};
    /***/
    private static final int[] SHAPE_S_1 = {0, 1, 1, 1, 1, 2, 2, 2};
    /***/
    private static final int[] SHAPE_S_2 = {0, 0, 1, 0, 1, 1, 2, 1};
    /***/
    private static final int[] SHAPE_Z_0 = {0, 0, 0, 1, 1, 1, 1, 2};
    /***/
    private static final int[] SHAPE_Z_1 = {0, 2, 1, 1, 1, 2, 2, 1};
    /***/
    private static final int[] SHAPE_Z_2 = {0, 1, 1, 0, 1, 1, 2, 0};
    /***/
    private static final int[] SHAPE_T_0 = {0, 1, 1, 0, 1, 1, 1, 2};
    /***/
    private static final int[] SHAPE_T_1 = {0, 1, 1, 1, 1, 2, 2, 1};
    /***/
    private static final int[] SHAPE_T_2 = {1, 0, 1, 1, 1, 2, 2, 1};
    /***/
    private static final int[] SHAPE_T_3 = {0, 1, 1, 0, 1, 1, 2, 1};
    /**
     * The list containing all the shapes of the Tetriminos.
     */
    public static final char[] SHAPES = {'I', 'O', 'L', 'J', 'S', 'Z', 'T'};
    /**
     * The colors for each Tetrimino.
     */
    private static final Color[] COLORS = {
            Color.CYAN,
            Color.YELLOW,
            Color.ORANGERED,
            Color.DARKBLUE,
            Color.LIME,
            Color.RED,
            Color.PURPLE
    };

    /**
     * Create the Tetrimino.
     *
     * @param shape The shape of the Tetrimino.
     * @param x     The X position.
     * @param y     The Y position.
     */
    public Tetrimino(char shape, double x, double y) {
        this.shape = shape;
        for (int i = 0; i < SHAPES.length; i++) {
            if (SHAPES[i] == shape) {
                color = COLORS[i];
                break;
            }
        }
        this.x = x;
        this.y = y;
        this.rotation = -1;
        rotate(1);
    }

    /**
     * Rotate the Tetrimino.
     *
     * @param amount The amount of turns.
     */
    public void rotate(int amount) {
        rotation += amount;
        if (shape == 'I') {
            if (rotation % MAX_ROTATIONS == 0 || rotation % MAX_ROTATIONS == 2) {
                make(SHAPE_I_0, 1);
            } else if (rotation % MAX_ROTATIONS == 1) {
                make(SHAPE_I_1, 1);
            } else {
                make(SHAPE_I_2, 1);
            }
        } else if (shape == 'O') {
            make(SHAPE_0, 2);
        } else if (shape == 'L') {
            if (rotation % MAX_ROTATIONS == 0) {
                make(SHAPE_L_0, 1);
            } else if (rotation % MAX_ROTATIONS == 1) {
                make(SHAPE_L_1, 1);
            } else if (rotation % MAX_ROTATIONS == 2) {
                make(SHAPE_L_2, 1);
            } else {
                make(SHAPE_L_3, 1);
            }
        } else if (shape == 'J') {
            if (rotation % MAX_ROTATIONS == 0) {
                make(SHAPE_J_0, 1);
            } else if (rotation % MAX_ROTATIONS == 1) {
                make(SHAPE_J_1, 1);
            } else if (rotation % MAX_ROTATIONS == 2) {
                make(SHAPE_J_2, 1);
            } else {
                make(SHAPE_J_3, 1);
            }
        } else if (shape == 'S') {
            if (rotation % MAX_ROTATIONS == 0 || rotation % MAX_ROTATIONS == 2) {
                make(SHAPE_S_0, 1);
            } else if (rotation % MAX_ROTATIONS == 1) {
                make(SHAPE_S_1, 1);
            } else {
                make(SHAPE_S_2, 1);
            }
        } else if (shape == 'Z') {
            if (rotation % MAX_ROTATIONS == 0 || rotation % MAX_ROTATIONS == 2) {
                make(SHAPE_Z_0, 1);
            } else if (rotation % MAX_ROTATIONS == 1) {
                make(SHAPE_Z_1, 1);
            } else {
                make(SHAPE_Z_2, 1);
            }
        } else if (shape == 'T') {
            if (rotation % MAX_ROTATIONS == 0) {
                make(SHAPE_T_0, 1);
            } else if (rotation % MAX_ROTATIONS == 1) {
                make(SHAPE_T_1, 1);
            } else if (rotation % MAX_ROTATIONS == 2) {
                make(SHAPE_T_2, 1);
            } else {
                make(SHAPE_T_3, 1);
            }
        }
    }

    /**
     * Create the matrix of the Tetrimino.
     *
     * @param data The array containing the y and x values of each rectangle.
     * @param nr   The number for the piece in the matrix. Maybe used in future development.
     */
    public void make(int[] data, int nr) {
        matrix = new int[MATRIX_ROWS][MATRIX_COLUMNS];
        for (int i = 0; i < data.length; i += 2) {
            matrix[data[i]][data[i + 1]] = nr;
        }
        draw();
    }

    /**
     * Function to draw the rectangles of the Tetrimino.
     */
    public void draw() {
        final int strokewidth = 3;
        final int archeight = 10;
        final int arcwidth = 10;
        getChildren().clear();
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] > 0) {
                    Rectangle rect = new Rectangle(x + c * blockSize, y + r * blockSize, blockSize, blockSize);
                    rect.setFill(color);
                    rect.setStroke(Color.BLACK);
                    rect.setStrokeWidth(strokewidth);
                    rect.setArcHeight(archeight);
                    rect.setArcWidth(arcwidth);
                    getChildren().add(rect);
                }
            }
        }
    }

    /**
     * Increase the Y position of the Tetrimino and redraw the shape.
     *
     * @param dy The amount of to be moved.
     */
    public void addY(double dy) {
        this.y += dy;
        draw();
    }

    /**
     * Increase the X position of the Tetrimino and redraw the shape.
     *
     * @param dx The amount of to be moved.
     */
    public void addX(double dx) {
        this.x += dx;
        draw();
    }

    /**
     * Remove the rectangle from the Tetrimino.
     *
     * @param r The rectangle to be removed.
     */
    public void remove(Rectangle r) {
        getChildren().remove(r);
    }

    /**
     * Get the Y position of the Tetrimino.
     * Not yet used.
     *
     * @return The the Y position of the Tetrimino.
     */
    public double getY() {
        return y;
    }

    /**
     * Hard set the X position of the Tetrimino.
     *
     * @param x The value of new X position.
     */
    public void setX(int x) {
        this.x = x;
        draw();
    }

    /**
     * Hard set the Y position of the Tetrimino.
     *
     * @param y The value of new Y position.
     */
    public void setY(int y) {
        this.y = y;
        draw();
    }

    /**
     * Return the shape of the Tetrimino.
     *
     * @return Shape of the Tetrimino.
     */
    public char getShape() {
        return shape;
    }
}
