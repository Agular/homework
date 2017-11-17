package praks11.ticketContainers;

import praks11.airlineservice.BoardingPass;

import java.util.ArrayList;
import java.util.List;

public class TicketArchive {

    private List<BoardingPass> boardingPasses;

    public TicketArchive() {
        this.boardingPasses = new ArrayList<>();
    }

    public int getSize(){
        return boardingPasses.size();
    }

    public synchronized void addTicket(BoardingPass boardingPass) {
        boardingPasses.add(boardingPass);
    }

    public List<BoardingPass> getTickets(){
        return boardingPasses;
    }
}
