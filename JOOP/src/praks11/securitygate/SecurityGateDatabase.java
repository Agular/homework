package praks11.securitygate;

import praks11.airlineservice.BoardingPass;
import praks11.ticketContainers.TicketArchive;
import praks11.ticketContainers.TicketStorage;

public interface SecurityGateDatabase extends Runnable{

    void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException;

    void setTicketStorage(TicketStorage ticketStorage);

    void setTicketArchive(TicketArchive ticketArchive);

    String getDetails();

    String getId();
}
