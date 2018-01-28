package city.environmenthq;

import city.CarEntry;
import city.environmenthq.EnvironmentHq;

public class CityResetter implements Runnable {

    private final int TIME_TO_SLEEP_BEFORE_EXECUTION_MS = 2000;

    private double newPollutionUnits;
    private EnvironmentHq environmentHq;

    public CityResetter(EnvironmentHq environmentHq, double newPollutionUnits) {
        this.newPollutionUnits = newPollutionUnits;
        this.environmentHq = environmentHq;
    }

    @Override
    public void run() {
        System.out.println("City resetter activated!");

        try {
            Thread.sleep(TIME_TO_SLEEP_BEFORE_EXECUTION_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(CarEntry carEntry: environmentHq.getCarEntries().values()){
            carEntry.setAllowedToDrive(true);
        }
        environmentHq.setDieselCarsAreProhibitedFromDriving(false);
        environmentHq.setPetrolCarsAreProhibitedFromDriving(false);
        environmentHq.getCity().setPollutionUnits(newPollutionUnits);
        environmentHq.wakeUpAllCars();
        environmentHq.setCityResetterIsActive(false);
        System.out.println("Resetter has resetted all cars.");
    }
}
