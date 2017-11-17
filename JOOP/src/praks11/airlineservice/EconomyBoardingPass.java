package praks11.airlineservice;

public class EconomyBoardingPass implements BoardingPass {

    private String firstName;
    private String lastName;
    private long ticketCode;
    private String gateId;

    public EconomyBoardingPass(String firstName, String lastName, long ticketCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ticketCode = ticketCode;
    }

    @Override
    public String getPassengerFirstName() {
        return firstName;
    }

    @Override
    public String getPassengerLastName() {
        return lastName;
    }

    @Override
    public long getTicketCode() {
        return ticketCode;
    }

    @Override
    public void setGateId(String id) {
        this.gateId = id;
    }

    @Override
    public String getGateId(){
        return gateId;
    }

    @Override
    public String getTicketInfo() {
        return "First name: " + firstName +" Last name: " + lastName + " Code: " + ticketCode;
    }
}
