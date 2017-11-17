package praks11.securitygate;

import praks11.airlineservice.BoardingPass;
import praks11.ticketContainers.TicketArchive;
import praks11.ticketContainers.TicketStorage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportSecurityGate implements SecurityGateDatabase {

    private TicketStorage ticketStorage;
    private TicketArchive ticketArchive;
    private List<BoardingPass> localTicketArchive;
    private int numberOfProcessedTickets;
    private String id;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public AirportSecurityGate(String id) {
        this.id = id;
        localTicketArchive = new ArrayList<>();
        numberOfProcessedTickets = 0;
    }

    @Override
    public void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException {
        numberOfProcessedTickets++;
        boardingPass.setGateId(id);
        System.out.println("Gate id: " + id + " Registreeritud pardakaart nr "
                + boardingPass.getTicketCode()
                + ": "
                + boardingPass.getPassengerFirstName()
                + " "
                + boardingPass.getPassengerLastName()
                + " Time: " + formatter.format(LocalTime.now()));
    }

    private synchronized void fetchTickets() {
        Optional<BoardingPass> boardingPass = ticketStorage.getNextTicket();
        boardingPass.ifPresent(pass -> {
            registerTicket(pass);
            localTicketArchive.add(pass);
        });
    }

    @Override
    public void setTicketStorage(TicketStorage ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    @Override
    public void setTicketArchive(TicketArchive ticketArchive) {
        this.ticketArchive = ticketArchive;
    }

    @Override
    public String getDetails() {
        return "Gate ID: " + id + "\n" +
                "Number of processed tickets: " + numberOfProcessedTickets;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            fetchTickets();
        }
        while (!localTicketArchive.isEmpty()) {
            ticketArchive.addTicket(localTicketArchive.remove(0));
        }
    }
}
