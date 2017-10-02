package kodutoo1.aircontroller;

import kodutoo1.airlineservice.AirlineTicketService;
import kodutoo1.securitygate.AirportSecurityGate;
import kodutoo1.airlineservice.LufthansaTicketService;
import kodutoo1.securitygate.SecurityGateDatabase;

public class AirportController {

    private AirlineTicketService airlineTicketService;
    private SecurityGateDatabase securityGateDatabase;

    public AirportController(){
        airlineTicketService = new LufthansaTicketService();
        securityGateDatabase = new AirportSecurityGate();
    }

    public void processTickets(){
        while (airlineTicketService.hasNextBoardingPass()) {
            securityGateDatabase.registerTicket(airlineTicketService.getNextBoardingPass());
        }
    }
}
