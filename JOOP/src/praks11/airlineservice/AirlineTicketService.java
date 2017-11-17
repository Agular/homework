package praks11.airlineservice;

import praks11.ticketContainers.TicketStorage;

public interface AirlineTicketService extends Runnable{
    boolean hasNextBoardingPass();

    void getNextBoardingPass();

    void setTicketStorage(TicketStorage ticketStorage);
}
