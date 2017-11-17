package praks11.airlineservice;

import praks11.ticketContainers.TicketArchive;
import praks11.ticketContainers.TicketStorage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LufthansaTicketService implements AirlineTicketService {

    private static int nextTicketId = 0;
    private TicketStorage ticketStorage;
    private final int AMOUNT_OF_MAX_GENERATEABLE_TICKETS = 3700;
    private final int MAX_AMOUNT_OF_TIME_DELAY = 1;
    private int amountOfGeneratedTickets = 0;
    private List<String> firstNamePool = Arrays.asList("Manuel", "Sierra", "Ragnar", "Mike", "Juliet");
    private List<String> lastNamePool = Arrays.asList("Felicitas", "Hotel", "Luga", "Harrison", "Fox");
    private Random randomTimeGenerator = new Random();

    @Override
    public synchronized void getNextBoardingPass() {
        if (ticketStorage == null) {
            throw new IllegalStateException("You need to assign a ticket storage to the service!");
        }
        if (hasNextBoardingPass()) {
            amountOfGeneratedTickets++;
            ticketStorage.addTicket(new EconomyBoardingPass(firstNamePool.get(amountOfGeneratedTickets % 5),
                    lastNamePool.get(amountOfGeneratedTickets % 5), getNextTicketId()));
        }
    }

    @Override
    public boolean hasNextBoardingPass() {
        return amountOfGeneratedTickets < AMOUNT_OF_MAX_GENERATEABLE_TICKETS;
    }

    public synchronized int getNextTicketId() {
        return ++nextTicketId;
    }

    @Override
    public void setTicketStorage(TicketStorage ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    @Override
    public void run() {
        while (hasNextBoardingPass()) {
            try {
                Thread.sleep(randomTimeGenerator.nextInt(MAX_AMOUNT_OF_TIME_DELAY));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getNextBoardingPass();
        }
    }
}
