package proovikontrolltoo2.vehicleshop;

import proovikontrolltoo2.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VehicleShop {

    private List<Vehicle> vehicles;

    public VehicleShop() {
        vehicles = new ArrayList<>();
    }

    public synchronized void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        notifyAll();
    }

    private synchronized Vehicle getVehicle(Predicate<Vehicle> buyCondition) {
        while (vehicles.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Vehicle> foundVehicles = vehicles.stream()
                .filter(buyCondition)
                .collect(Collectors.toList());
        if (!foundVehicles.isEmpty()) {
            return foundVehicles.remove(0);
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getVehicle(buyCondition);
        }
    }
}
