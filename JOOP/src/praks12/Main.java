package praks12;

import praks12.board.Board;
import praks12.person.Person;
import praks12.threads.PersonLikerThread;
import praks12.threads.PersonWriterThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();

        List<PersonLikerThread> likers = new ArrayList<>();
        List<PersonWriterThread> writers = new ArrayList<>();

        likers.add(new PersonLikerThread(new Person("Mike"), board));
        likers.add(new PersonLikerThread(new Person("Susy"), board));
        likers.add(new PersonLikerThread(new Person("Phil"), board));
        likers.add(new PersonLikerThread(new Person("Homer"), board));
        likers.add(new PersonLikerThread(new Person("Lisa"), board));
        likers.add(new PersonLikerThread(new Person("Marge"), board));
        likers.add(new PersonLikerThread(new Person("Bart"), board));

        writers.add(new PersonWriterThread(new Person("Apu"), board));
        writers.add(new PersonWriterThread(new Person("Moe"), board));
        writers.add(new PersonWriterThread(new Person("Cat"), board));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        writers.forEach(executorService::execute);
        likers.forEach(executorService::execute);

        Thread.sleep(7000);
        executorService.shutdownNow();

    }
}
