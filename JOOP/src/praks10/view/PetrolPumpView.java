package praks10.view;

import praks10.gasstation.PetrolPump;

import java.math.BigDecimal;

public class PetrolPumpView {

    public String getPetrolPumpJSON(PetrolPump petrolPump) {

        return "{\"id\": " + petrolPump.getId() + ", " +
                "\"pricePerLiterOfPetrol\": " + petrolPump.getPricePerLiterOfPetrol(BigDecimal.ONE) +
                "}";
    }
}
