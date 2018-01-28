package city.environmenthq;

import car.Car;
import engines.Engine;
import tires.JellyTires;
import tires.Tires;

import java.util.List;

public class RepairCar extends Car {

    private final int TIME_TO_WORK_ON_A_CAR_MS = 50;

    public RepairCar(int label, Engine engine, Tires tires) {
        super(label, engine, tires);
    }

    @Override
    public void run() {
        System.out.println("Repair car has been dispatched at intersection " + getCurrentIntersection().getLabel());
        while (!Thread.interrupted() && getEnvironmentHq().someCarsNeedTiresChanged()) {
            try {
                driveToNextCrossing();
                System.out.println("Repair car at intersection " + getCurrentIntersection().getLabel());
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            if (intersectionHasStoppedCars()) {
                try {
                    fixCarsWithNewTires();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
        getEnvironmentHq().setRepairCarHasBeenDispatched(false);
    }

    private boolean intersectionHasStoppedCars() {
        return getCurrentIntersection().hasCarsThatNeedTiresChange();
    }

    private void fixCarsWithNewTires() throws InterruptedException {
        List<Car> carsThatNeedTiresChanged = getCurrentIntersection().getCarsThatNeedTiresChange();
        while (!carsThatNeedTiresChanged.isEmpty()) {
            fixACarWithNewTires(carsThatNeedTiresChanged.remove(0));
        }
    }

    private void fixACarWithNewTires(Car car) throws InterruptedException {
        Thread.sleep(TIME_TO_WORK_ON_A_CAR_MS);
        getEnvironmentHq().carHasChangedTires(car.getLabel());
        car.repairTires(new JellyTires());
        System.out.println("Repair car fixed car " + car.getLabel());
    }
}
