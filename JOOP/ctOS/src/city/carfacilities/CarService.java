package city.carfacilities;

import car.Car;
import car.CarInformation;
import engines.ElectricEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarService {

    private final int TIME_TO_WORK_ON_THE_CAR_MS = 50;
    private String name;
    private HashMap<Integer, ServedCarInformation> servedCarInformations;

    public CarService(String name) {
        servedCarInformations = new HashMap<>();
        this.name = name;
    }

    public synchronized void serveCar(Car car) throws InterruptedException {
        workOnTheCar();
        registerCar(car.getCarInfo());
    }

    private void registerCar(CarInformation carInformation) {
        if (servedCarInformations.containsKey(carInformation.getLabel())) {
            ServedCarInformation servedCarInfo = servedCarInformations.get(carInformation.getLabel());
            servedCarInfo.setEngine(carInformation.getEngine());
            servedCarInfo.setTires(carInformation.getTires());
            servedCarInfo.setNumberOfStreetsDriven(carInformation.getNumberOfStreetsDriven());
        } else {
            servedCarInformations.put(carInformation.getLabel(), new ServedCarInformation(carInformation));
        }

        //System.out.println("CarService " + name + " served car " + carInformation.getLabel());
        //System.out.println(getServicedCarsByStrategy(new AllDieselCarsStrategy()));
    }

    private void workOnTheCar() throws InterruptedException {
        Thread.sleep(TIME_TO_WORK_ON_THE_CAR_MS);
    }

    private List<ServedCarInformation> getServicedCarsByStrategy(ServedCarStrategy strategy){
        return strategy.getListOfSearchedCars(new ArrayList<ServedCarInformation>(servedCarInformations.values()));
    }


    public void serveCarWithEngineChange(Car car) throws InterruptedException {
        car.setEngine(new ElectricEngine());
        serveCar(car);
    }

    public String getName(){
        return name;
    }
}
