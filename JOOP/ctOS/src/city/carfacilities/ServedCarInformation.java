package city.carfacilities;

import car.CarInformation;
import engines.Engine;
import streetnetwork.Intersection;
import tires.Tires;

public class ServedCarInformation extends CarInformation {

    private int timesServed;

    public ServedCarInformation(int label, Tires tires, Engine engine, int numberOfStreetsDriven, Intersection currentIntersection) {
        super(label, tires, engine, numberOfStreetsDriven, currentIntersection);
    }

    public ServedCarInformation(CarInformation carInformation){
        super();
        super.setLabel(carInformation.getLabel());
        super.setEngine(carInformation.getEngine());
        super.setTires(carInformation.getTires());
        super.setNumberOfStreetsDriven(carInformation.getNumberOfStreetsDriven());
        super.setCurrentIntersection(carInformation.getCurrentIntersection());
        timesServed = 1;
    }

    public void increaseTimesServed(){
        timesServed++;
    }

    public int getTimesServed(){
        return timesServed;
    }
}
