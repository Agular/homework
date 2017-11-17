package praks11.aircontroller;

import praks11.airlineservice.AirlineTicketService;
import praks11.airlineservice.BoardingPass;
import praks11.securitygate.BackupGate;
import praks11.securitygate.SecurityGateDatabase;
import praks11.threadoverwatchers.SecurityGateOverwatcher;
import praks11.ticketContainers.TicketArchive;
import praks11.ticketContainers.TicketStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AirportController {

    private List<AirlineTicketService> airlineTicketServices;
    private List<SecurityGateDatabase> securityGateDatabases;
    private static TicketStorage ticketStorage;
    private static TicketArchive ticketArchive;

    public AirportController() {
        airlineTicketServices = new ArrayList<>();
        securityGateDatabases = new ArrayList<>();
        ticketStorage = new TicketStorage();
        ticketArchive = new TicketArchive();
    }

    public void addAirlineTicketService(AirlineTicketService airlineTicketService) {
        airlineTicketService.setTicketStorage(ticketStorage);
        airlineTicketServices.add(airlineTicketService);
    }

    public void addSecurityGateDatabase(SecurityGateDatabase securityGateDatabase) {
        securityGateDatabase.setTicketStorage(ticketStorage);
        securityGateDatabases.add(securityGateDatabase);
        securityGateDatabase.setTicketArchive(ticketArchive);
    }

    private int getAmountOfArchivedTickets() {
        return ticketArchive.getSize();
    }

    public void startSystem() throws InterruptedException {
        Thread backupGateThread = new Thread(new SecurityGateOverwatcher(new BackupGate(), 20_000));
        List<Thread> ticketServiceThreads = airlineTicketServices.stream().map(Thread::new).collect(Collectors.toList());
        List<Thread> gateDatabaseThreads = securityGateDatabases.stream().map(gate ->
                new Thread(new SecurityGateOverwatcher(gate, 10_000))
        ).collect(Collectors.toList());

        ticketServiceThreads.forEach(Thread::start);
        gateDatabaseThreads.forEach(Thread::start);
        System.out.println("Gates and ticket services are active!");
        backupGateThread.start();
        for (Thread gateDatabaseThread : gateDatabaseThreads) {
            gateDatabaseThread.join();
        }
        backupGateThread.interrupt();
        backupGateThread.join();
        System.out.println("Tickets are archived");

        securityGateDatabases.parallelStream().forEach(gate -> {
            long count = ticketArchive.getTickets().parallelStream().filter(s -> s.getGateId().equals(gate.getId())).count();
            Optional<BoardingPass> examplePass = ticketArchive.getTickets().parallelStream()
                    .filter(s -> s.getGateId().equals(gate.getId())).findFirst();
            if (examplePass.isPresent()) {
                System.out.println("Gate ID: " + gate.getId() + "\n" +
                        "Number of processed tickets: " + count + "\n" +
                        "Example Ticket -> " + examplePass.get().getTicketInfo());
            }
        });

        //securityGateDatabases.stream().map(SecurityGateDatabase::getDetails).forEach(System.out::println);
    }
}
