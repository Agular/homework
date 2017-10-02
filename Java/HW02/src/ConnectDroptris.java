/**
 * Created by Ragnar on 21.03.2016.
 * Class for establishing connection with the server.
 * v0.01 - just started, making the first steps. Added basic functions to be completed. Will ask a friend.
 * Somewhat completed the connect() function, but no response.
 * v0.02 - robust, but somewhat complete connection.
 */


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/***
 * The class that connects to the server and receives/sends data.
 */
public class ConnectDroptris {
    /**
     * Ip of the server.
     */
    String ip = "nisu.cs.ttu.ee";
    /**
     * Port of the server.
     */
    private final int port = 13131;
    /**
     * The printwriter for sending messages to server.
     */
    static PrintWriter outServer;
    /**
     * The reader for getting the messages from the server.
     */
    InputStreamReader inServer;
    /**
     * The size of the read buffer.
     */
    private final int bufferSize = 1000;
    /**
     * The char array to receive messages.
     */
    char[] input = new char[bufferSize];
    /**
     * The parser to parse messages into JSON objects.
     */
    JsonParser parser = new JsonParser();
    /**
     * GSON class.
     */
    Gson gson = new Gson();
    /**
     * For converting the input into gameboard array.
     */
    StateGson stateGson;

    /**
     * Establish the connection with the server.
     *
     * @param connectionString The JSON string used to connect with the server.
     * @throws IOException If connection fails.
     */
    public void connect(String connectionString) throws IOException {
        System.out.println("Connecting to "
                + ip
                + " on port "
                + port);
        Socket client = new Socket(ip, port);
        outServer = new PrintWriter(client.getOutputStream(), true);
        System.out.println(connectionString);
        outServer.println(connectionString);
        inServer = new InputStreamReader(client.getInputStream());
        inServer.read(input, 0, bufferSize);
        System.out.println("Server says " + new String(input));
    }

    /**
     * Get the next block info from the server.
     *
     * @return The next block.
     * @throws IOException If the connection fails.
     */
    public String getNextBlock() throws IOException {
        input = new char[bufferSize];
        inServer.read(input, 0, bufferSize);
        String inputStr = new String(input).trim();
        JsonObject o = parser.parse(inputStr).getAsJsonObject();
        if (!o.has("block")) {
            return getNextBlock();
        } else {
            System.out.println("block: " + o.get("block").getAsString());
            return o.get("block").getAsString();
        }
    }

    /**
     * Send the column and rotation to the server.
     *
     * @param actionInfo Contains the column and rotation info.
     */
    public static void sendAction(int[] actionInfo) {
        outServer.println("{\n"
                + "\"column\": " + actionInfo[0] + ",\n"
                + "\"rotation\": " + actionInfo[1] + "\n"
                + "}\n");
    }

    /**
     * Get score data from the server.
     *
     * @return The score.
     * @throws IOException If the connection fails.
     */
    public int askScoreData() throws IOException {
        input = new char[bufferSize];
        outServer.println("{\n"
                + "\"parameter\": \"score\"\n"
                + "}");
        inServer.read(input, 0, bufferSize);
        String inputStr = new String(input).trim();
        JsonObject score = parser.parse(inputStr).getAsJsonObject();
        if (!score.has("value") || !score.get("parameter").getAsString().equals("score")) {
            return askScoreData();
        } else {
            return score.get("value").getAsInt();
        }
    }

    /**
     * Ask the server for the state of the game.
     *
     * @throws IOException If connection fails.
     */
    public void askState() throws IOException {
        input = new char[bufferSize];
        outServer.println("{\n"
                + "\"parameter\": \"state\"\n"
                + "}");
        inServer.read(input, 0, bufferSize);
        String inputstr = new String(input).trim();
        JsonObject o = parser.parse(inputstr).getAsJsonObject();
        if (!o.has("value")) {
            askState();
        } else {
            stateGson = gson.fromJson(o, StateGson.class);
        }
    }

    /**
     * Ask the server for the status of the game.
     *
     * @throws IOException If connection fails.
     */
    public void askGameStatus() throws IOException {
        input = new char[bufferSize];
        outServer.println("{\n"
                + "\"parameter\": \"status\"\n"
                + "}");
        inServer.read(input, 0, bufferSize);
        System.out.println(new String(input));
    }

    /**
     * Ask the server for the level of the game.
     *
     * @throws IOException If connection fails.
     */
    public void askLevel() throws IOException {
        input = new char[bufferSize];

        outServer.println("{\n"
                + "\"parameter\": \"status\"\n"
                + "}");
        inServer.read(input, 0, bufferSize);
        System.out.println(new String(input));
    }

    /**
     * Return the received gameboard.
     *
     * @return The gameboard.
     */
    public int[][] getGameBoard() {
        return stateGson.returnValue();
    }
}
