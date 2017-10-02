import java.io.IOException;

/**
 * Homework 02 - Droptris AI.
 * v0.01 - imported the new template.
 */
public class HW02 {
    /**
     * The connector.
     */
    static ConnectDroptris c;

    /**
     * The main method. You can use this to initialize the game.
     * Tester will not execute the main method.
     *
     * @param args Arguments from command line.
     * @throws IOException If connection fails.
     */
    public static void main(String[] args) throws IOException {
        run("{"
                + "\"uniid\": \"raluga\",\n"
                + "\"seed\": 11111111,\n"
                + "\"level\": 2 ,\n"
                + "\"lookahead\": 0\n"
                + "}");
    }

    /**
     * Optional setup. This method will be called
     * before the game is started. You can do some
     * precalculations here if needed.
     * <p>
     * If you don't need to precalculate anything,
     * just leave it empty.
     */
    public static void setup() {
    }

    /**
     * The method to execute your AI.
     *
     * @param connectionString JSON-formatted connection string.
     *                         If you implement Socket connection yourself
     *                         you should use this string directly when connecting.
     *                         If you use DroptrisConnection, you can ignore that.
     * @return The final score. You should read the score from the server.
     * @throws IOException If the connection fails.
     */
    public static int run(String connectionString) throws IOException {
        int score = 0;
        final int milliSeconds = 1000000;
        Shapes s = new Shapes();
        String nextBlock;
        boolean gameOn = true;
        ConnectDroptris c = new ConnectDroptris();
        c.connect(connectionString);
        while (gameOn) {
            long startTime = System.nanoTime();
            nextBlock = c.getNextBlock();
            c.askState();
            s.setGameBoard(c.getGameBoard());
            s.decideShape(nextBlock);
            score = c.askScoreData();
            System.out.println("Score: " + score);
            long endTime = System.nanoTime();
            System.out.println("Time spent: " + (endTime - startTime) / milliSeconds);
        }
        return score;
    }
}
