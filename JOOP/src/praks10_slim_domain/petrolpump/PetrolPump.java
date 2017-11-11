package praks10_slim_domain.petrolpump;

import praks10_slim_domain.petrolstrategies.PetrolStrategy;
import praks10_slim_domain.petrolstrategies.SimplePetrolStrategy;

public class PetrolPump {

    private int id;
    private GasTank gasTank;
    private PetrolStrategy petrolStrategy;

    public PetrolPump(int id, GasTank gasTank) {
        this.id = id;
        this.gasTank = gasTank;
        this.petrolStrategy = new SimplePetrolStrategy();
    }

    public int getId() {
        return id;
    }

    public GasTank getGasTank() {
        return gasTank;
    }

    public PetrolStrategy getPetrolStrategy() {
        return petrolStrategy;
    }

    public void setPetrolStrategy(PetrolStrategy petrolStrategy) {
        this.petrolStrategy = petrolStrategy;
    }
}
