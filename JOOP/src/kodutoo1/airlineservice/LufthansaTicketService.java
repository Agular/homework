package kodutoo1.airlineservice;

import java.util.ArrayList;

public class LufthansaTicketService implements AirlineTicketService{

    private ArrayList<EconomyBoardingPass> boardingPassDB = new ArrayList<>();

    public LufthansaTicketService(){
        boardingPassDB.add(new EconomyBoardingPass("Manuel", "Felicitas", 219462));
        boardingPassDB.add(new EconomyBoardingPass("Sierra", "Hotel", 267753));
        boardingPassDB.add(new EconomyBoardingPass("Ragnar", "Luga", 223113));
        boardingPassDB.add(new EconomyBoardingPass("Mike", "Harrison", 288053));
        boardingPassDB.add(new EconomyBoardingPass("Juliet", "Fox", 204311));
    }

    @Override
    public boolean hasNextBoardingPass() {
        return !boardingPassDB.isEmpty();
    }

    @Override
    public BoardingPass getNextBoardingPass() {
        if(boardingPassDB.size() > 0){
            return boardingPassDB.remove(0);
        } else {
            return null;
        }
    }
}
