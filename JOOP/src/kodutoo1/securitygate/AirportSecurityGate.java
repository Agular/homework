package kodutoo1.securitygate;

import kodutoo1.airlineservice.BoardingPass;

public class AirportSecurityGate implements SecurityGateDatabase {

    @Override
    public void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException {
        System.out.println("Registreeritud pardakaart nr "
                + boardingPass.getTicketCode()
                + ": "
                + boardingPass.getPassengerFirstName()
                + " "
                + boardingPass.getPassengerLastName());
    }
}
