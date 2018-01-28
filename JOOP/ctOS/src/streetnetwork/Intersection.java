package streetnetwork;

import car.Car;
import city.carfacilities.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author of streetnetwork solution: Samed Düzçay
 * Graph solution taken from his GitHub: https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58
 * Renamed vertex to intersection to make more logical with the cars.
 */

public class Intersection {

    private int uniqueLabel;
    private CarService carService;
    private boolean isStartingIntersection;
    private List<Car> carsThatNeedTiresChange;

    public Intersection(int uniqueLabel) {
        super();
        this.uniqueLabel = uniqueLabel;
        isStartingIntersection = false;
        carsThatNeedTiresChange = new ArrayList<>();
    }

    public Intersection(int uniqueLabel, boolean isStartingIntersection) {
        super();
        this.uniqueLabel = uniqueLabel;
        this.isStartingIntersection = isStartingIntersection;
        carsThatNeedTiresChange = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Intersection)) return false;

        Intersection _obj = (Intersection) obj;
        return _obj.uniqueLabel == uniqueLabel && _obj.carService == carService && _obj.isStartingIntersection == isStartingIntersection;
    }

    @Override
    public int hashCode() {
        int result = uniqueLabel;
        if (carService != null) {
            result = 31 * result + carService.hashCode();
        } else {
            result = 31 * result + 1;
        }
        result = 31 * result + Boolean.hashCode(isStartingIntersection);
        return result;
    }

    public int getLabel() {
        return uniqueLabel;
    }

    public void setLabel(int uniqueLabel) {
        this.uniqueLabel = uniqueLabel;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public Optional<CarService> getCarService() {
        return Optional.ofNullable(carService);
    }

    public boolean isStartingIntersection() {
        return isStartingIntersection;
    }

    public void stopForEmergencyTiresChange(Car car){
        carsThatNeedTiresChange.add(car);
    }

    public void leaveAfterEmergencyTiresChange(Car car){
        carsThatNeedTiresChange.remove(car);
    }

    public boolean hasCarsThatNeedTiresChange(){
        return !carsThatNeedTiresChange.isEmpty();
    }

    public List<Car> getCarsThatNeedTiresChange(){
        return carsThatNeedTiresChange;
    }

    @Override
    public String toString() {
        return String.valueOf(uniqueLabel);
    }
}