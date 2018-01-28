package tires;

import streetnetwork.Street;

public interface Tires {

    String getType();
    void reduceHealth(Street street);
    boolean areHealthy();
}
