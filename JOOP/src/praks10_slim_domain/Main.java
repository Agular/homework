package praks10_slim_domain;

import praks10_slim_domain.petrolpump.GasTank;
import praks10_slim_domain.petrolpump.PetrolPump;
import praks10_slim_domain.petrolpumpservice.PetrolPumpService;
import praks10_slim_domain.petrolstrategies.PetrolStrategy;
import praks10_slim_domain.view.PetrolPumpView;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args){

        PetrolStrategy petrolStrategy = PetrolStrategy.WeirdPetrolStrategy();
        PetrolPump petrolPump = new PetrolPump(29, new GasTank(BigDecimal.TEN));
        PetrolPumpView petrolPumpView = new PetrolPumpView();
        System.out.println(petrolPumpView.getPetrolPumpJSON(petrolPump, new PetrolPumpService()));
    }
}
