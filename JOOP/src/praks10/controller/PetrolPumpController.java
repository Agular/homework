package praks10.controller;

import praks10.gasstation.PetrolPump;
import praks10.view.PetrolPumpView;

public class PetrolPumpController {

    public String getPetrolPumpJSON(PetrolPump petrolPump, PetrolPumpView petrolPumpView) {

        return petrolPumpView.getPetrolPumpJSON(petrolPump);
    }
}
