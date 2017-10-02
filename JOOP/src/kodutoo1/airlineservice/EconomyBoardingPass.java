package kodutoo1.airlineservice;

public class EconomyBoardingPass implements BoardingPass {

    private String firstName;
    private String lastName;
    private long ticketCode;

    public EconomyBoardingPass(String firstName, String lastName, long ticketCode){
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
}
