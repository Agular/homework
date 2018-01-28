package praks12.threads;

import praks12.board.Board;
import praks12.person.Person;

public class PersonLikerThread extends Thread {

    private Person person;
    private Board board;

    public PersonLikerThread(Person person, Board board) {
        this.person = person;
        this.board = board;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                person.likeLastMessageOnBoard(board);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Liker " + person.getName() + " ended liking");
    }
}
