package praks10_slim_domain.petrolpumpservice;

import praks10_slim_domain.petrolpump.PetrolPump;

import java.math.BigDecimal;

public class PetrolPumpService {

    public BigDecimal tank(PetrolPump petrolPump, BigDecimal amount) {
        if (petrolPump.getGasTank().getRemainingPetrol().compareTo(amount) == -1) {
            return petrolPump.getGasTank().getGas(petrolPump.getGasTank().getRemainingPetrol());
        } else {
            return petrolPump.getGasTank().getGas(amount);
        }
    }

    public void refill(PetrolPump petrolPump) {
        petrolPump.getGasTank().refill();
    }

    public BigDecimal getPricePerLiterOfPetrol(PetrolPump petrolPump, BigDecimal amountOfPetrolBought) {
        return petrolPump.getPetrolStrategy().getPricePerLiterOfPetrol(petrolPump.getGasTank(), amountOfPetrolBought);
    }
}
