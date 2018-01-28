package praks12.person;

import praks12.board.Board;
import praks12.message.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Person {

    private String name;
    private Random randomGenerator = new Random();
    private List<String> randomMessages = Arrays.asList(
            "I ate a cookie today, really!",
            "My cat just farted, so funny xD",
            "God, this is the most beautiful cake ever!",
            "What if we are all just the results of a computer?");

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void writeMessageToBoard(Board board) {
        board.addMessage(new Message(this, randomMessages.get(randomGenerator.nextInt(4))));
    }

    public void likeLastMessageOnBoard(Board board) throws InterruptedException {
        board.getLastMessage().like(this);
    }
}
