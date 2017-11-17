package praks11;

import praks11.aircontroller.AirportController;
import praks11.airlineservice.LufthansaTicketService;
import praks11.securitygate.AirportSecurityGate;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        AirportController controller = new AirportController();
        controller.addAirlineTicketService(new LufthansaTicketService());
        controller.addAirlineTicketService(new LufthansaTicketService());
        controller.addSecurityGateDatabase(new AirportSecurityGate("1"));
        controller.addSecurityGateDatabase(new AirportSecurityGate("2"));
        controller.startSystem();
    }
}
