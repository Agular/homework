package city;

import engines.Engine;
import streetnetwork.Intersection;
import tires.Tires;

public class CarEntry {

    private int label;
    private Engine engine;
    private Tires tires;
    private int numberOfStreetsDriven;
    private boolean allowedToDrive;
    private Intersection currentIntersection;

    public CarEntry(int label, Engine engine, Tires tires, int numberOfStreetsDriven,
                    Intersection currentIntersection, boolean allowedToDrive) {
        this.label = label;
        this.engine = engine;
        this.tires = tires;
        this.numberOfStreetsDriven = numberOfStreetsDriven;
        this.currentIntersection = currentIntersection;
        this.allowedToDrive = allowedToDrive;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public int getNumberOfStreetsDriven() {
        return numberOfStreetsDriven;
    }

    public void setNumberOfStreetsDriven(int numberOfStreetsDriven) {
        this.numberOfStreetsDriven = numberOfStreetsDriven;
    }

    public boolean isAllowedToDrive() {
        return allowedToDrive;
    }

    public void setAllowedToDrive(boolean allowedToDrive) {

        this.allowedToDrive = allowedToDrive;
    }

    public Intersection getCurrentIntersection() {
        return currentIntersection;
    }

    public void setCurrentIntersection(Intersection currentIntersection) {
        this.currentIntersection = currentIntersection;
    }

    @Override
    public String toString() {
        return "Label: " + label + " Tires: " + tires.getType() + " Engine: " + engine.getType() +
                " NumberOfStreetsDriven: " + numberOfStreetsDriven + " CurrentIntersection: " + currentIntersection.getLabel()
                + " AllowedToDrive: " + allowedToDrive;
    }
}

