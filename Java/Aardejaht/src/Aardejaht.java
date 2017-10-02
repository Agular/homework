import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ragnar on 04.02.2016.
 * A treasure hunting game. v0.01
 * A big home homework for the Java course.
 * Version updates:
 * v0.01 - started the homework.
 * v0.02 - created print function, GenerateGameBoard() needs surrounding numbers;
 */
public class Aardejaht {
    static Scanner scanner = new Scanner(System.in);
    static Integer[][] gameBoard;
    static String[][] printBoard;
    static Integer[] gameInfo = new Integer[3];
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("Sisesta M (ridade arv), N (veergude arv), X (aarete arv):");
        String[] rawGameInfo = scanner.next().split(",");
        for (int i = 0; i < 3; i++) {
            gameInfo[i] = Integer.parseInt(rawGameInfo[i]);
        }
        GenerateGameBoards(gameInfo[0], gameInfo[1], gameInfo[2]);
        PrintGameBoard();
    }

    public static void GenerateGameBoards(int col, int row, int treasureAmount) {
        gameBoard = new Integer[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                gameBoard[i][j] = 0;
            }
        }
        for (int i = 0; i < treasureAmount; i++) {
            int randCol = rand.nextInt(col);
            int randRow = rand.nextInt(row);
            gameBoard[randCol][randRow] = -1;
        }
    }

    public static void PrintGameBoard() {
        for (int i = 0; i < gameInfo[0]; i++) {
            for (int j = 0; j < gameInfo[1]; j++) {
                System.out.printf("%4d", gameBoard[i][j]);
            }
            System.out.print("\n");
        }
    }
}
