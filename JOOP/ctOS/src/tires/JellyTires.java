package tires;

import streetnetwork.Street;

public class JellyTires implements Tires {

    @Override
    public String getType() {
        return "Jelly";
    }

    @Override
    public void reduceHealth(Street street) { }

    @Override
    public boolean areHealthy() {
        return true;
    }
}
