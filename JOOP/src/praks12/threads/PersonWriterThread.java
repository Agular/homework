package praks12.threads;

import praks12.board.Board;
import praks12.person.Person;

public class PersonWriterThread extends Thread {

    private Person person;
    private Board board;

    public PersonWriterThread(Person person, Board board) {
        this.person = person;
        this.board = board;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("Writer " + person.getName() + " ended writing");
                break;
            }
            person.writeMessageToBoard(board);
        }
    }
}
