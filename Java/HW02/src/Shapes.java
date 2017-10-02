import java.util.TreeMap;

/**
 * Created by Ragnar on 27.03.2016.
 * For each piece there is a function.
 * NEEDS BACKUP MOVES FOR JOLT PIECES + optimization.
 */
public class Shapes {
    /***/
    public static final int BOARD_HEIGHT = 20;
    /***/
    public static final int BOARD_WIDTH = 10;
    /***/
    public static final int NINE = 9;
    /***/
    public static final int EIGHT = 8;
    /**
     * The gameboard of the game.
     */
    int[][] gameBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];
    /**
     * The heigts of each column.
     */
    int[] heights = new int[BOARD_WIDTH];
    /**
     * The minimum height of the gameboard.
     */
    int minHeight;
    /**
     * Holds the possible move in order.
     */
    TreeMap<String, int[]> moves;
    /**
     * How good is a move. Goes from 0-inf with 0 being the best move.
     */
    Integer value = 0;

    /**
     * Set the board.
     *
     * @param gameBoard The given gameboard.
     */
    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Find the heights for the gameboard.
     */
    public void setHeights() {
        minHeight = BOARD_HEIGHT;
        if (gameBoard[0] == null) {
            System.out.println("gameboard0");
        }
        for (int j = 0; j < gameBoard[0].length; j++) {
            for (int i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i][j] != 0) {
                    heights[j] = BOARD_HEIGHT - i;
                    if (BOARD_HEIGHT - i < minHeight) {
                        minHeight = BOARD_HEIGHT - i;
                    }
                    break;
                } else if (i == gameBoard.length - 1) {
                    heights[j] = 0;
                    minHeight = 0;
                }
            }
        }
    }

    /**
     * Decide which shape function to implement.
     *
     * @param shape The string of the shape.
     */
    public void decideShape(String shape) {
        setHeights();
        for (int[] row : gameBoard) {
            for (int col : row) {
                System.out.format("%2d", col);
            }
            System.out.println();
        }
        if (shape.equals("O")) {
            shapeO();
        } else if (shape.equals("I")) {
            shapeI();
        } else if (shape.equals("L")) {
            shapeL();
        } else if (shape.equals("J")) {
            shapeJ();
        } else if (shape.equals("Z")) {
            shapeZ();
        } else if (shape.equals("S")) {
            shapeS();
        } else {
            shapeT();
        }
    }

    /**
     * Algorithm for placing "O".
     * Best 1: if the place is flat and the lowest.
     * Backup: if there is a 1 square or more difference, prefer lowest.
     */
    public void shapeO() {
        moves = new TreeMap<String, int[]>();
        for (int col = 0; col < heights.length; col++) {
            value = (heights[col] - minHeight) / 2;
            if (col < NINE && heights[col] == heights[col + 1]) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col + 1] - heights[col] >= 1) {
                moves.put(value + 2 + 1 + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col > 0 && col < NINE && heights[col - 1] - heights[col] >= 1) {
                moves.put(value + 2 + 1 + "" + heights[col] + "" + col, new int[]{col - 1, 0});
            }
        }
        ConnectDroptris.sendAction(moves.firstEntry().getValue());
    }

    /**
     * Algorithm for placing "I".
     * Best1: The place is flat, sorts by lowest.
     * Best2: If there is a height difference of 2 or greater with one of the neighbor columns.
     * Backup: Place it vertically on the lowest point.
     */
    public void shapeI() {
        moves = new TreeMap<String, int[]>();
        for (int col = 0; col < heights.length; col++) {
            value = (heights[col] - minHeight) / 2;
            if (col < NINE - 2 && heights[col] == heights[col + 1] && heights[col + 1] == heights[col + 2]
                    && heights[col + 2] == heights[col + 2 + 1]) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 1});
            }
            if (col > 0 && heights[col - 1] - heights[col] >= 2) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col + 1] - heights[col] >= 2) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            moves.put(value * 2 + "" + heights[col] + "" + col, new int[]{col, 0});
        }
        ConnectDroptris.sendAction(moves.firstEntry().getValue());
    }

    /**
     * Algorithm for placing "L".
     * Best1: Place it flat.
     * Best2: Place it flat and the left is one point lower than the middle point.
     * Best3:Place it upside down, left is 2 units higer than right.
     * PseudoBest: Place it originally on flat surface.
     */
    public void shapeL() {
        moves = new TreeMap<String, int[]>();
        for (int col = 0; col < heights.length; col++) {
            value = (heights[col] - minHeight) / 2;
            if (col < EIGHT && heights[col] == heights[col + 1] && heights[col + 1] == heights[col + 2]) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 2 + 1});
            }
            if (col < EIGHT && heights[col + 1] == heights[col + 2] && heights[col + 1] - heights[col] == 1) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 1});
            }
            if (col < NINE && heights[col] - heights[col + 1] == 2) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 2});
            }
            if (col < NINE && heights[col] == heights[col + 1]) {
                moves.put(value + 1 + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col] - heights[col + 1] > 2) {
                moves.put(value + 2 + "" + heights[col] + "" + col, new int[]{col, 2});
            }
            if (col < NINE && heights[col + 1] - heights[col] >= 1) {
                moves.put(value + 2 + 1 + "" + heights[col + 1] + "" + col, new int[]{col, 0});
            }
            if (col > 0 && heights[col - 1] - heights[col] >= 1) {
                moves.put(value + 2 + 1 + "" + heights[col - 1] + "" + col, new int[]{col, 0});
            }
        }
        ConnectDroptris.sendAction(moves.firstEntry().getValue());
    }

    /***/
    public void shapeJ() {
        moves = new TreeMap<String, int[]>();
        for (int col = 0; col < heights.length; col++) {
            value = (heights[col] - minHeight) / 2;
            if (col < EIGHT && heights[col] == heights[col + 1] && heights[col + 1] == heights[col + 2]) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 1});
            }
            if (col < EIGHT && heights[col] == heights[col + 1] && heights[col + 1] - heights[col + 2] == 1) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 2 + 1});
            }
            if (col < NINE && heights[col + 1] - heights[col] == 2) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 2});
            }
            if (col < NINE && heights[col] == heights[col + 1]) {
                moves.put(value + 1 + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col + 1] - heights[col] > 2) {
                moves.put(value + 1 + "" + heights[col] + "" + col, new int[]{col, 2});
            }
            if (col < NINE && heights[col + 1] - heights[col] >= 1) {
                moves.put(value + 2 + 1 + "" + heights[col + 1] + "" + col, new int[]{col, 0});
            }
            if (col > 0 && heights[col - 1] - heights[col] >= 1) {
                moves.put(value + 2 + 1 + "" + heights[col - 1] + "" + col, new int[]{col, 0});
            }
        }
        ConnectDroptris.sendAction(moves.firstEntry().getValue());
    }

    /***/
    public void shapeZ() {
        moves = new TreeMap<String, int[]>();
        for (int col = 0; col < heights.length; col++) {
            value = (heights[col] - minHeight) / 2;
            if (col < EIGHT && heights[col + 1] - heights[col] == -1 && heights[col + 1] == heights[col + 2]) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col + 1] - heights[col] == 1) {
                moves.put(value + 1 + "" + heights[col] + "" + col, new int[]{col, 1});
            }
            if (col < EIGHT && heights[col] == heights[col + 1] && heights[col + 1] == heights[col + 2]) {
                moves.put(value + 2 + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col + 1] - heights[col] >= 1) {
                moves.put(value + 2 + "" + heights[col] + "" + col, new int[]{col, 1});
            }
        }
        ConnectDroptris.sendAction(moves.firstEntry().getValue());
    }

    /***/
    public void shapeS() {
        moves = new TreeMap<String, int[]>();
        for (int col = 0; col < heights.length; col++) {
            value = (heights[col] - minHeight) / 2;
            if (col < EIGHT && heights[col] == heights[col + 1] && heights[col + 2] - heights[col + 1] == 1) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col] - heights[col + 1] == 1) {
                moves.put(value + 1 + "" + heights[col] + "" + col, new int[]{col, 1});
            }
            if (col < EIGHT && heights[col] == heights[col + 1] && heights[col + 1] == heights[col + 2]) {
                moves.put(value + 2 + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col] - heights[col + 1] >= 1) {
                moves.put(value + 2 + "" + heights[col] + "" + col, new int[]{col, 1});
            }
        }
        ConnectDroptris.sendAction(moves.firstEntry().getValue());
    }

    /***/
    public void shapeT() {
        moves = new TreeMap<String, int[]>();
        for (int col = 0; col < heights.length; col++) {
            value = (heights[col] - minHeight) / 2;
            if (col < EIGHT && heights[col] == heights[col + 2] && heights[col] - heights[col + 1] == 1) {
                moves.put(value + "" + heights[col] + "" + col, new int[]{col, 2});
            }
            if (col < EIGHT && heights[col] == heights[col + 1] && heights[col + 1] == heights[col + 2]) {
                moves.put(value + 1 + "" + heights[col] + "" + col, new int[]{col, 0});
            }
            if (col < NINE && heights[col] - heights[col + 1] == 1) {
                moves.put(value + 2 + "" + heights[col] + "" + col, new int[]{col, 2 + 1});
            }
            if (col < NINE && heights[col + 1] - heights[col] == 1) {
                moves.put(value + 1 + "" + heights[col] + "" + col, new int[]{col, 1});
            }
        }
        ConnectDroptris.sendAction(moves.firstEntry().getValue());
    }
}
