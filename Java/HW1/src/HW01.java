import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ragnar on 04.02.2016.
 * A treasure hunting game. v0.01
 * A big home homework for the Java course.
 * Version updates:
 * v0.01 - started the homework.
 * v0.02 - created print function, GenerateGameBoard() needs surrounding numbers;
 * v0.03 - imported the code into a new template, finished side functions, created minesweeper style fill function
 * added OutBoard print function and initialized the printBoard with "." .
 * v.0.04 - added minesweeper opening style, created playGame function to operate the game
 * and started the MM and HeatCold styles
 * v.0.1 - added Heat&Cold gamemode and functions to fulfill it, game should be operating normally if
 * one does not intend to break it. Seems pretty cool. Needs more input checks and constants.
 * v.0.11 - changed the game to use global variables instead of poorly understandable gameInfo[].
 */
public class HW01 {
    /**
     * The mighty tool of Java. The scanner/fax machine of
     */
    static Scanner scanner = new Scanner(System.in);
    /**
     * The internal spot for saving the game state.
     */
    static Integer[][] gameBoard;
    /**
     * The variable that is used to print out the game state for the player.
     */
    static String[][] printBoard;
    /**
     * The global variable for the rows of gameboard.
     */
    static Integer gameRows;
    /**
     * The global variable for the colums of gameboard.
     */
    static Integer gameCols;
    /**
     * The global variable for the treasures on gameboard.
     */
    static Integer gameTreasures;
    /**
     * The all-might RNG generator. May the Randomness be with you.
     */
    static Random rand = new Random();
    /**
     * The boolean that determines the amount of games to be played.
     */
    static boolean games5 = false;
    /**
     * The boolean that determines the game mode.
     */
    static boolean defaultMode = true;
    /**
     * Number of maximum games that can be played.
     */
    public static final Integer MAXIMUM_GAMES = 5;
    /**
     *
     * */
    public static final Integer MAX_VALUE_OF_CELL = 8;

    /**
     * Can't believe I have to document string the main function.
     * Asks the player input, implements the main game function playGame and asks
     * if the user wishes to play again.
     *
     * @param args Whatever this is. don't know it excactly.
     *             And it shall remain unknown forever for the user.
     */
    public static void main(String[] args) {
        Integer[] gameInfo;
        do {
            do {
                System.out.println("Insert M (amount of rows), N (amount of columns), X (amount of treasures):");
                String[] rawGameInfo = scanner.nextLine().replaceAll(" ", "").split(",");
                gameInfo = new Integer[rawGameInfo.length];
                for (int i = 0; i < gameInfo.length; i++) {
                    gameInfo[i] = Integer.parseInt(rawGameInfo[i]);
                }
            } while (!createMap(gameInfo[0], gameInfo[1], gameInfo[2]));
            gameRows = gameInfo[0];
            gameCols = gameInfo[1];
            gameTreasures = gameInfo[2];
            System.out.println("Gamemode: Minesweeper(0) or Cold/Warm(1)");
            if (scanner.nextLine().equals("1")) {
                defaultMode = false;
            }
            System.out.println("Wish to play MM(y/n)? MM conists of 5 games.");
            if (scanner.nextLine().equals("y")) {
                games5 = true;
            }
            playGame();
            System.out.println("Wish to play again? (y / n)");
        } while (scanner.nextLine().equals("y"));
    }

    /**
     * A function for the developer to check for bugs
     * and apply fixes. Prints out all the positions.
     */
    public static void printInGameBoard() {
        for (int i = 0; i < gameRows; i++) {
            for (int j = 0; j < gameCols; j++) {
                System.out.printf("%4d", gameBoard[i][j]);
            }
            System.out.print("\n");
        }
    }

    /**
     * Function that prints out the game nicely for the player.
     */
    public static void printOutGameBoard() {
        System.out.print("    ");
        for (int j = 0; j < gameCols; j++) {
            System.out.printf("%4s", j);
        }
        System.out.println();
        System.out.print("\n");
        for (int i = 0; i < gameRows; i++) {
            System.out.printf("%4s", i);
            for (int j = 0; j < gameCols; j++) {
                System.out.printf("%4s", printBoard[i][j]);
            }
            System.out.print("\n");
        }
    }

    /**
     * Value to return in makeMove in case
     * the cell was empty.
     */
    public static final int CELL_EMPTY = 0;

    /**
     * Value to return in makeMove in case
     * the cell contained treasure.
     */
    public static final int CELL_TREASURE = 1;

    /**
     * Value to return in makeMove in case
     * the cell does not exist.
     */

    public static final int CELL_ERROR = -1;

    /**
     * Makes move to cell in certain row and column
     * and returns the contents of the cell.
     * Use CELL_* constants in return.
     * Please, stupid nullMove.
     *
     * @param row Row to make move to.
     * @param col Column to make move to.
     * @return Contents of the cell.
     */
    public static int makeMove(int row, int col) {
        if (gameBoard == null || row >= gameBoard.length || col >= gameBoard[0].length || row < 0 || col < 0) {
            return CELL_ERROR;
        } else if (gameBoard[row][col] >= 0 && gameBoard[row][col] <= MAX_VALUE_OF_CELL) {
            return CELL_EMPTY;
        } else if (gameBoard[row][col] == -1) {
            return CELL_TREASURE;
        }
        return CELL_ERROR;
    }

    /**
     * The main part of the game.
     * The user inputs the rows and colums of the gameboard and the amount of treasures.
     * Then the user chooses the gamemode and the amount of games.
     * Uses global variables to easen the readability and make it easier to understand.
     */
    public static void playGame() {
        int gamesToPlay = 1;
        if (games5) gamesToPlay = MAXIMUM_GAMES;
        int gamesPlayed = 0;
        int treasureFound = 0;
        int digAmount = 0;
        int totalDigAmount = 0;
        String[] coordinates;
        while (gamesPlayed < gamesToPlay) {
            createMap(gameRows, gameCols, gameTreasures);
            if (defaultMode) {
                fillGameboardMinesweeperStyle();
            }
            System.out.format("Happy treasure hunting! Game: %s\n", gamesPlayed + 1);
            do {
                printOutGameBoard();
                // uncomment to see the gameBoardPrintInGameBoard();
                System.out.format("\nDigs: %d, treasures left: %d\n", digAmount, gameTreasures - treasureFound);
                System.out.println("Where shall we dig?(row, columns): ");
                coordinates = scanner.nextLine().replaceAll(" ", "").split(",");
                int cordX = Integer.parseInt(coordinates[0]);
                int cordY = Integer.parseInt(coordinates[1]);
                switch (makeMove((cordX), cordY)) {
                    case CELL_ERROR:
                        System.out.println("Ooops, it seems you are out of bounds. Try again!");
                        break;
                    case CELL_TREASURE:
                        if (!printBoard[cordX][cordY].equals(".")) {
                            System.out.println("You've already found this treasure. Try again!");
                        } else {
                            digAmount++;
                            treasureFound++;
                            printBoard[cordX][cordY] = "X";
                            System.out.print("\nTreasure!\n");
                        }
                        break;
                    case CELL_EMPTY:
                        if (!printBoard[cordX][cordY].equals(".")) {
                            System.out.println("You've already dug here. Try again!");
                        }
                        if (defaultMode) {
                            if (gameBoard[cordX][cordY] == 0) {
                                digAmount++;
                                openGameboardMinesweeperStyle(cordX, cordY);
                            } else {
                                digAmount++;
                                printBoard[cordX][cordY] = Integer.toString(gameBoard[cordX][cordY]);
                            }
                        } else {
                            System.out.format("\nThe nearest treasure is %d units away.\n",
                                    searchGameboardHeatColdStyle(cordX, cordY));
                        }
                        break;
                    default:
                        System.out.println("Nice try breaking the game");
                        break;
                }
            } while (treasureFound < gameTreasures);
            gamesPlayed++;
            treasureFound = 0;
            totalDigAmount += digAmount;
            printOutGameBoard();
            System.out.format("You found all the treasures! Total amount of digs: %d\n\n", digAmount);
            if (games5) {
                digAmount = 0;
                gameRows += 2;
                gameCols += 2;
                gameTreasures += 2;
            }

        }
        if (games5) {
            System.out.format("You've completed all 5 games. Total amount of digs: %d\n", totalDigAmount);
        }
    }

    /**
     * For testing.
     * Creates a map with certain measures and treasures.
     * As this is a static method which doesn't return anything (void),
     * you should store the created map internally.
     * This means you can choose your own implementation of how to store
     * the map.
     * The treasures should be put on the map randomly using setCell method.
     *
     * @param row            Height of the map.
     * @param col            Width of the map.
     * @param treasureAmount The number of treasures on the map.
     * @return Whether map was created.
     */
    public static boolean createMap(int row, int col, int treasureAmount) {
        if (row < 0 || col < 0 || treasureAmount < 1 || treasureAmount > row * col) {
            return false;
        } else {
            gameBoard = new Integer[row][col];
            printBoard = new String[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    setCell(i, j, 0);
                    printBoard[i][j] = ".";
                }
            }
            for (int i = 0; i < treasureAmount; i++) {
                int randCol = rand.nextInt(col);
                int randRow = rand.nextInt(row);
                if (gameBoard[randRow][randCol] == -1) {
                    i--;
                } else {
                    setCell(randRow, randCol, -1);
                }
            }
            return true;
        }
    }

    /**
     * Fills the gameboard with numbers equivalent to the number of mines around them.
     */
    public static void fillGameboardMinesweeperStyle() {
        for (int i = 0; i < gameRows; i++) {
            for (int j = 0; j < gameCols; j++) {
                if (gameBoard[i][j] == -1) {  // finds every mine
                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {
                            //looks up boundaries and adds +1 if there is no mine at that place
                            if (i + m > -1 && i + m < gameRows && j + n > -1 && j + n < gameCols
                                    && gameBoard[i + m][j + n] != -1) {
                                gameBoard[i + m][j + n]++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Opens all the 0 recursively and all the cells around them.
     * The worm shall grow bigger with every click.
     *
     * @param row Row index.
     * @param col Column index.
     */
    public static void openGameboardMinesweeperStyle(int row, int col) {
        printBoard[row][col] = "0";
        for (int m = -1; m < 2; m++) {
            for (int n = -1; n < 2; n++) {
                //looks up boundaries and opens up the other 0's
                if (row + m > -1 && row + m < gameRows && col + n > -1 && col + n < gameCols
                        && gameBoard[row + m][col + n] == 0 && printBoard[row + m][col + n].equals(".")) {
                    openGameboardMinesweeperStyle(row + m, col + n);
                }
            }
        }
        for (int m = -1; m < 2; m++) {
            for (int n = -1; n < 2; n++) {
                //looks up boundaries and opens up the for the player
                if (row + m > -1 && row + m < gameRows && col + n > -1 && col + n < gameCols) {
                    printBoard[row + m][col + n] = Integer.toString(gameBoard[row + m][col + n]);
                }
            }
        }
    }

    /**
     * Searches for the nearest mine next to player chose coordinates.
     *
     * @param row Row index.
     * @param col Column index.
     * @return The length for the nearest unopened mine.
     */
    public static int searchGameboardHeatColdStyle(int row, int col) {
        double radius = Integer.MAX_VALUE;
        for (int i = 0; i < gameRows; i++) {
            for (int j = 0; j < gameCols; j++) {
                if (gameBoard[i][j] == -1 && printBoard[i][j].equals(".")) {
                    if (Math.sqrt(Math.pow(i - row, 2) + Math.pow(j - col, 2)) < radius) {
                        radius = Math.sqrt(Math.pow(i - row, 2) + Math.pow(j - col, 2));
                    }
                }
            }
        }
        return (int) radius;
    }

    /**
     * Sets the cell value for the active map (created earlier using
     * createMap method).
     * This method is required to test certain maps
     *
     * @param row          Row index.
     * @param col          Column index.
     * @param cellContents The value of the cell.
     * @return Whether the cell value was set.
     */
    public static boolean setCell(int row, int col, int cellContents) {
        if (gameBoard == null || row < 0 || col < 0 || row >= gameBoard.length || col > gameBoard[0].length) {
            return false;
        } else {
            gameBoard[row][col] = cellContents;
            return true;
        }
    }

    /**
     * Creates a nice string including the gamedata for highscores.
     *
     * @return gameString - Contains the gameMode data.
     */
    public static String generateGameString() {
        String gameString = "";
        if (defaultMode) {
            gameString += "Minesweeper ";

        } else {
            gameString += "Heat&Cold ";
        }
        if (games5) {
            gameString = "MM " + gameString + (gameRows - MAXIMUM_GAMES) + "X" + (gameCols - MAXIMUM_GAMES) + "X"
                    + gameTreasures;
        } else {
            gameString += gameRows + "X" + gameCols + "X" + gameTreasures;
        }
        return gameString;
    }
}
