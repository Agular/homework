package praks10;

import praks10.gasstation.GasTank;
import praks10.gasstation.PetrolPump;
import praks10.petrolstrategies.PetrolStrategy;
import praks10.view.PetrolPumpView;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args){

        PetrolStrategy petrolStrategy = PetrolStrategy.WeirdPetrolStrategy();
        PetrolPump petrolPump = new PetrolPump(29, new GasTank(BigDecimal.TEN));
        PetrolPumpView petrolPumpView = new PetrolPumpView();
        System.out.println(petrolPumpView.getPetrolPumpJSON(petrolPump));
    }
}
