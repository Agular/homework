package tires;

import streetnetwork.Street;

public class RegularTires implements Tires {

    private final int DEFAULT_HEALTH = 3;
    private final int TIRES_BROKEN_HEALTH = 0;
    private int health;

    public RegularTires() {
        health = DEFAULT_HEALTH;
    }

    @Override
    public String getType() {
        return "Regular";
    }

    @Override
    public void reduceHealth(Street street) {
        if (street.isBroken()) {
            health--;
            if(health < 0){
                health = TIRES_BROKEN_HEALTH;
            }
        }
    }

    @Override
    public boolean areHealthy() {
        return health > TIRES_BROKEN_HEALTH;
    }
}
