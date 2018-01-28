package city.environmenthq;

import car.CarInformation;
import city.CarEntry;
import city.City;
import engines.ElectricEngine;
import streetnetwork.Intersection;
import tires.JellyTires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EnvironmentHq {

    private final double DIESEL_POLLUTION_LEVEL = 400.0f;
    private final double PETROL_POLLUTION_LEVEL = 500.0f;

    private HashMap<Integer, CarEntry> carEntries;
    private List<Integer> carsThatNeedTiresChanged;
    private City city;

    private boolean cityResetterIsActive;
    private boolean dieselCarsAreProhibitedFromDriving;
    private boolean petrolCarsAreProhibitedFromDriving;
    private boolean repairCarHasBeenDispatched;

    public EnvironmentHq(City city) {
        carEntries = new HashMap<>();
        this.city = city;
        cityResetterIsActive = false;
        dieselCarsAreProhibitedFromDriving = false;
        petrolCarsAreProhibitedFromDriving = false;
        carsThatNeedTiresChanged = new ArrayList<>();
        repairCarHasBeenDispatched = false;
    }

    public synchronized void updateEnvironmentHqWithCarInformation(CarInformation carInformation) {
        int carLabel = carInformation.getLabel();
        if (carEntries.containsKey(carLabel)) {
            double deltaNumberOfStreetsDriven = carInformation.getNumberOfStreetsDriven() - carEntries.get(carLabel).getNumberOfStreetsDriven();
            carEntries.get(carLabel).setNumberOfStreetsDriven(carInformation.getNumberOfStreetsDriven());
            carEntries.get(carLabel).setCurrentIntersection(carInformation.getCurrentIntersection());
            city.setPollutionUnits(city.getPollutionUnits() + deltaNumberOfStreetsDriven * carInformation.getEngine().getPollutionUnits());
        } else {
            CarEntry carEntry = new CarEntry(carInformation.getLabel(), carInformation.getEngine(),
                    carInformation.getTires(), carInformation.getNumberOfStreetsDriven(),
                    carInformation.getCurrentIntersection(), true);
            carEntries.put(carLabel, carEntry);
            city.setPollutionUnits(city.getPollutionUnits() +
                    carInformation.getNumberOfStreetsDriven() * carInformation.getEngine().getPollutionUnits());
        }
        updateCityTrafficProtocol();
        System.out.println("Updated car entry: " + carEntries.get(carLabel));
        System.out.println("City pollution level: " + city.getPollutionUnits());

    }

    private void updateCityTrafficProtocol() {
        if (city.getPollutionUnits() > DIESEL_POLLUTION_LEVEL && !dieselCarsAreProhibitedFromDriving) {
            prohibitCarsFromDrivingWithEngineType("Diesel");
            dieselCarsAreProhibitedFromDriving = true;
        }
        if (city.getPollutionUnits() > PETROL_POLLUTION_LEVEL && !petrolCarsAreProhibitedFromDriving) {
            prohibitCarsFromDrivingWithEngineType("Petrol");
            petrolCarsAreProhibitedFromDriving = true;
        }

        if (!cityResetterIsActive && (dieselCarsAreProhibitedFromDriving || petrolCarsAreProhibitedFromDriving)) {
            long numberOfCarsWithInternalCombustion = getNumberOfCarsWithInternalCombustion();
            double newPollutionUnits;
            if (numberOfCarsWithInternalCombustion > 70) {
                newPollutionUnits = 0.4 * city.getPollutionUnits();
            } else {
                newPollutionUnits = 0.0f;
            }
            cityResetterIsActive = true;
            activateCityResetter(newPollutionUnits);
        }
    }

    private void prohibitCarsFromDrivingWithEngineType(String engineType) {
        carEntries.values().stream().filter(e -> e.getEngine().getType().equals(engineType))
                .forEach(e -> e.setAllowedToDrive(false));
    }

    private void activateCityResetter(double newPollutionUnits) {
        CityResetter cityResetter = new CityResetter(this, newPollutionUnits);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(cityResetter);
    }

    public synchronized boolean blockUntilCarAllowedToDrive(int carLabel) throws InterruptedException {
        boolean wasStopped = false;
        while (!carEntries.get(carLabel).isAllowedToDrive()) {
            wasStopped = true;
            wait();
        }
        return wasStopped;
    }

    long getNumberOfCarsWithInternalCombustion() {
        return carEntries.values().stream().filter(e -> e.getEngine().getType().equals("Diesel") ||
                e.getEngine().getType().equals("Petrol")).count();
    }

    public synchronized void wakeUpAllCars() {
        notifyAll();
    }

    public void setCityResetterIsActive(boolean cityResetterIsActive) {
        this.cityResetterIsActive = cityResetterIsActive;
    }

    public HashMap<Integer, CarEntry> getCarEntries() {
        return carEntries;
    }

    public City getCity() {
        return city;
    }

    public void setDieselCarsAreProhibitedFromDriving(boolean dieselCarsAreProhibitedFromDriving) {
        this.dieselCarsAreProhibitedFromDriving = dieselCarsAreProhibitedFromDriving;
    }

    public void setPetrolCarsAreProhibitedFromDriving(boolean petrolCarsAreProhibitedFromDriving) {
        this.petrolCarsAreProhibitedFromDriving = petrolCarsAreProhibitedFromDriving;
    }

    public void notifyOfEngineChange(CarInformation carInfo) {
        carEntries.get(carInfo.getLabel()).setEngine(carInfo.getEngine());
    }

    public void notifyOfTiresChange(CarInformation carInfo) {
        carEntries.get(carInfo.getLabel()).setTires(carInfo.getTires());
    }

    void carHasChangedTires(Integer carLabel) {
        carsThatNeedTiresChanged.remove(carLabel);
    }

    boolean someCarsNeedTiresChanged() {
        return !carsThatNeedTiresChanged.isEmpty();
    }

    public void addCarThatNeedsTiresChanged(CarInformation carInfo) {
        carsThatNeedTiresChanged.add(carInfo.getLabel());
        if (!repairCarHasBeenDispatched) {
            dispatchServiceCar(carInfo.getCurrentIntersection());
        }
    }

    private void dispatchServiceCar(Intersection brokenCarCurrentIntersection) {
        List<Intersection> intersections = city.getStreetNetwork().getIntersections();
        Intersection startingCarIntersection = null;
        for (Intersection intersection: intersections){
            if(!intersection.equals(brokenCarCurrentIntersection)){
                startingCarIntersection = intersection;
            }
        }
        RepairCar repairCar = new RepairCar(-1, new ElectricEngine(), new JellyTires());
        repairCar.setEnvironmentHq(this);
        repairCar.setStreetNetwork(city.getStreetNetwork());
        repairCar.setIntersection(startingCarIntersection);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(repairCar);
    }

    public void setRepairCarHasBeenDispatched(boolean repairCarHasBeenDispatched) {
        this.repairCarHasBeenDispatched = repairCarHasBeenDispatched;
    }
}
