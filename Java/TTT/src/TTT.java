/**
 * Created by Ago on 2016-05-05.
 */
public class TTT {
    public static int rowtemp;
    public static int coltemp;
    public static int rowidx;
    public static int colidx;
    public static int scoore = -1000000;
    static int[][] board = {
            {2, 0, 1},
            {0, 1, 1},
            {2, 0, 2}
    };
    /*
    0 - tühi
    1 - pla1
    2 - pla2
     */

    /*
    012
    345
    678
     */
    static int[][] winCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public static void main(String[] args) {
        int score = minimax(board, 1, 0);
        System.out.println("score:" + score);
        System.out.println(rowidx);
        System.out.println(colidx);
        System.out.println(scoore);
    }

    public static int getScore(int[][] board) {
        for (int[] winCombination : winCombinations) {
            int count = 0;
            for (int field : winCombination) {
                int row = field / 3;
                int col = field % 3;
                if (board[row][col] == 0) {
                    // tühi ruut, ei saa olla võitu
                    count = 0;
                    break;
                }
                count += board[row][col];
            }
            if (count == 3) return 100;
            if (count == 6) return -100;
        }
        return 0;

    }

    public static int minimax(int[][] board, int player, int depth) {
        if (depth == 3) {
            // hinda seisu
            return getScore(board);
        }
        /*

        iga võimaliku käigu kohta:
            tee see käik lauale
            hinda seisu => minimax
            võta käik tagasi

         */
        int bestScore = -1000;
        if (player == 2) {
            bestScore = Integer.MAX_VALUE;
        }
        for (int row = 0; row < 3; row++) {
            int rowx;
            int rowy;
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    // kui on võimalik siia käia

                    board[row][col] = player;
                    int score = minimax(board, (player % 2) + 1, depth + 1);
                    board[row][col] = 0;

                    if (player == 1) {
                        if (score > bestScore) {
                            bestScore = score;
                            if (depth == 0) {
                                rowtemp = row;
                                coltemp = col;
                            }
                        }
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
        }
        if (depth == 0 && bestScore > scoore) {
            rowidx = rowtemp;
            colidx = coltemp;
            scoore = bestScore;
        }
        return bestScore;
    }
}
