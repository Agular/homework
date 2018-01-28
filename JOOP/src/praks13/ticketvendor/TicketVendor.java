package praks13.ticketvendor;

import praks13.ticket.Ticket;
import praks13.ticket.TicketStorage;

import java.util.Random;
import java.util.concurrent.Callable;

public class TicketVendor implements Callable<Integer> {

    private String id;
    private TicketStorage ticketStorage;
    private static int nextTicketId = 0;
    private int soldTickets = 0;
    private final int TIME_TO_SLEEP;
    private Random randWeightGenerator = new Random();

    public TicketVendor(String id, int timeToSleep) {
        this.id = id;
        this.TIME_TO_SLEEP = timeToSleep;
    }

    public void setTicketStorage(TicketStorage ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    private synchronized int getNextTicketId() {
        return ++nextTicketId;
    }

    private void generateTicket() {
        int weight = 20 + randWeightGenerator.nextInt(101);
        ticketStorage.addTicket(new Ticket(getNextTicketId(), weight));
        soldTickets++;
    }

    @Override
    public Integer call() throws Exception {
        while (!Thread.interrupted()) {
            generateTicket();
            try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException e) {
                System.out.println("Vendor: " + id + " has stopped working!");
                break;
            }
        }
        return soldTickets;
    }
}
