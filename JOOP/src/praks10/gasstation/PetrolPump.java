package praks10.gasstation;

import praks10.petrolstrategies.PetrolStrategy;
import praks10.petrolstrategies.SimplePetrolStrategy;

import java.math.BigDecimal;

public class PetrolPump {

    private int id;
    private GasTank gasTank;
    private PetrolStrategy petrolStrategy;

    public PetrolPump(int id, GasTank gasTank) {
        this.id = id;
        this.gasTank = gasTank;
        this.petrolStrategy = new SimplePetrolStrategy();
    }

    public BigDecimal tank(BigDecimal amount) {
        if (gasTank.getRemainingPetrol().compareTo(amount) == -1) {
            return gasTank.getGas(gasTank.getRemainingPetrol());
        } else {
            return gasTank.getGas(amount);
        }
    }

    public void refill() {
        gasTank.refill();
    }

    public BigDecimal getPricePerLiterOfPetrol(BigDecimal amountOfPetrolBought) {
        return petrolStrategy.getPricePerLiterOfPetrol(gasTank, amountOfPetrolBought);
    }

    public int getId() {
        return id;
    }

    public void setNewPetrolStrategy(PetrolStrategy petrolStrategy) {
        this.petrolStrategy = petrolStrategy;
    }
}
