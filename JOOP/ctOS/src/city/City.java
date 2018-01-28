package city;

import car.Car;
import city.environmenthq.EnvironmentHq;
import streetnetwork.Intersection;
import streetnetwork.StreetNetwork;

import java.util.List;
import java.util.Random;

public class City {

    private String name;
    private double pollutionUnits;
    private StreetNetwork streetNetwork;
    private EnvironmentHq environmentHq;
    private Random randomGenerator = new Random();

    public City(String name, StreetNetwork streetNetwork) {
        this.name = name;
        this.streetNetwork = streetNetwork;
        this.environmentHq = new EnvironmentHq(this);
        pollutionUnits = 0;
    }

    public void registerCar(Car car){
        car.setEnvironmentHq(environmentHq);
        car.setStreetNetwork(streetNetwork);
        List<Intersection> startingIntersections = streetNetwork.getStartingIntersections();
        Intersection startingIntersection = startingIntersections.
                get(randomGenerator.nextInt(startingIntersections.size()));
        car.setIntersection(startingIntersection);
    }

    public void setPollutionUnits(double pollutionUnits) {
        this.pollutionUnits = pollutionUnits;
    }

    public double getPollutionUnits() {
        return pollutionUnits;
    }

    public String getName(){
        return name;
    }

    public StreetNetwork getStreetNetwork() {
        return streetNetwork;
    }
}
