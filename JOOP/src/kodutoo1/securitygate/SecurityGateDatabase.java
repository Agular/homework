package kodutoo1.securitygate;

import kodutoo1.airlineservice.BoardingPass;

public interface SecurityGateDatabase {
    public void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException;
}
