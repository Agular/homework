package praks10_slim_domain.view;

import praks10_slim_domain.petrolpump.PetrolPump;
import praks10_slim_domain.petrolpumpservice.PetrolPumpService;

import java.math.BigDecimal;

public class PetrolPumpView {

    public String getPetrolPumpJSON(PetrolPump petrolPump, PetrolPumpService service) {

        return "{\"id\": " + petrolPump.getId() + ", " +
                "\"pricePerLiterOfPetrol\": " + service.getPricePerLiterOfPetrol(petrolPump, BigDecimal.ONE) +
                "}";
    }
}
