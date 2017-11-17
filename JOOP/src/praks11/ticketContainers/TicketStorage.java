package praks11.ticketContainers;

import praks11.airlineservice.BoardingPass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketStorage {

    private List<BoardingPass> boardingPasses;

    public TicketStorage() {
        this.boardingPasses = new ArrayList<>();
    }

    public synchronized void addTicket(BoardingPass boardingPass) {
        boardingPasses.add(boardingPass);
    }

    public synchronized Optional<BoardingPass> getNextTicket() {
        if (!boardingPasses.isEmpty()) {
            return Optional.of(boardingPasses.remove(0));
        }
        return Optional.empty();
    }

    public synchronized boolean hasNextTicket() {
        return !boardingPasses.isEmpty();
    }
}
