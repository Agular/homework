package praks13.ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketStorage {

    private List<Ticket> tickets = new ArrayList<>();

    public synchronized void addTicket(Ticket ticket) {
        tickets.add(ticket);
        notifyAll();
    }

    public synchronized Ticket getTicket() {
            while (tickets.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return tickets.remove(0);
    }
}
