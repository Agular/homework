package tetris;


import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Holds the tetriminos and returns the pieces.
 * v0.01: created the pieces, TetrisShape class to hold them.
 */
public class Tetrimino {
    /***/
    private static final Random RANDOM = new Random();
    /**
     * Piece shaped * *
     *              * *
     * */
    private static final TetrisShape O = new TetrisShape(new int[][]{
            {1, 1},
            {1, 1}}
            , Color.YELLOW);
    /**
     * Piece shaped * * * *
     *
     * */
    private static final TetrisShape I = new TetrisShape(new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}}
            , Color.CYAN);
    /**
     * Piece shaped * * *
     *                  *
     * */
    private static final TetrisShape J = new TetrisShape(new int[][]{
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}}
            , Color.ORANGE);
    /**
     * Piece shaped * * *
     *              *
     * */
    private static final TetrisShape L = new TetrisShape(new int[][]{
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}}
            , Color.BLUE);
    /**
     * Piece shaped * *
     *                * *
     * */
    private static final TetrisShape Z = new TetrisShape(new int[][]{
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}}
            , Color.RED);
    /**
     * Piece shaped  * *
     *             * *
     * */
    private static final TetrisShape S = new TetrisShape(new int[][]{
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}}
            , Color.GREEN);
    /**
     * Piece shaped  *
     *             * * *
     * */
    private static final TetrisShape T = new TetrisShape(new int[][]{
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}}
            , Color.PURPLE);

    /**
     * The class that holds the certain shapes and their colors.
     */
    private static class TetrisShape {
        /**
         * Color of specified shape.
         * */
        private final Color color;
        /**
         * Matrix of specified shape.
         * */
        private final int[][] matrix;
        /**
         * The official constructor.
         * */
        private TetrisShape(int[][] matrix, Color color) {
            this.color = color;
            this.matrix = matrix;
        }
    }
}
