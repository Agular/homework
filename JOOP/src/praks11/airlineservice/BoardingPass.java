package praks11.airlineservice;

public interface BoardingPass {
    String getPassengerFirstName();

    String getPassengerLastName();

    long getTicketCode();

    void setGateId(String id);

    String getGateId();

    String getTicketInfo();
}
