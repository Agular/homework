package praks10_slim_domain.controller;

import praks10_slim_domain.petrolpump.PetrolPump;
import praks10_slim_domain.petrolpumpservice.PetrolPumpService;
import praks10_slim_domain.view.PetrolPumpView;

public class PetrolPumpController {

    public String getPetrolPumpJSON(PetrolPump petrolPump, PetrolPumpView petrolPumpView) {


        return petrolPumpView.getPetrolPumpJSON(petrolPump, new PetrolPumpService());
    }
}
