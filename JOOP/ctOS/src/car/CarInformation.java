package car;

import engines.Engine;
import streetnetwork.Intersection;
import tires.Tires;

public class CarInformation {

    private int label;
    private Tires tires;
    private Engine engine;
    private int numberOfStreetsDriven;
    private Intersection currentIntersection;

    public CarInformation(int label, Tires tires, Engine engine, int numberOfStreetsDriven, Intersection currentIntersection) {
        this.label = label;
        this.tires = tires;
        this.engine = engine;
        this.numberOfStreetsDriven = numberOfStreetsDriven;
        this.currentIntersection = currentIntersection;
    }

    public CarInformation() {

    }

    public int getLabel() {
        return label;
    }

    public Tires getTires() {
        return tires;
    }

    public Engine getEngine() {
        return engine;
    }

    public int getNumberOfStreetsDriven() {
        return numberOfStreetsDriven;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setNumberOfStreetsDriven(int numberOfStreetsDriven) {
        this.numberOfStreetsDriven = numberOfStreetsDriven;
    }

    public Intersection getCurrentIntersection() {
        return currentIntersection;
    }

    public void setCurrentIntersection(Intersection currentIntersection) {
        this.currentIntersection = currentIntersection;
    }

    @Override
    public String toString() {
        return "Label: " + label + " Tires: " + tires.getType() +
                " Engine: " + engine.getType() + " NumberOfStreetsDriven: " + numberOfStreetsDriven +
                " CurrentIntersection: " + currentIntersection.getLabel();
    }
}
