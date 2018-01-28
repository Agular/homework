package car;

import city.environmenthq.EnvironmentHq;
import engines.Engine;
import streetnetwork.Intersection;
import streetnetwork.Street;
import streetnetwork.StreetNetwork;
import tires.Tires;

import java.util.List;
import java.util.Random;

public abstract class Car implements Runnable {

    private final int MIN_TIMES_STOPPED_BEFORE_ENGINE_UPGRADE = 2;
    private final int UPDATE_HQ_EVERY_N_STREETS = 5;
    private final int ASK_ALLOWED_TO_DRIVE_EVERY_N_STREETS = 7;
    private final double CHANCE_TO_UPGRADE_ENGINE = 1 / 2;
    private final int MIN_TIME_TO_DRIVE_MS = 30;
    private final int MAX_TIME_TO_DRIVE_MS = 200;

    private Engine engine;
    private Tires tires;
    private StreetNetwork streetNetwork;

    private Intersection currentIntersection;
    private EnvironmentHq environmentHq;

    private final Integer label;
    private int numberOfDrivenStreets;
    private Random randomGenerator;
    private int numberOfTimesStopped;
    private boolean needsToChangeEngine;
    private boolean hasChangedEngine;

    public Car(int label, Engine engine, Tires tires) {
        this.engine = engine;
        this.tires = tires;
        this.label = label;
        numberOfDrivenStreets = 0;
        numberOfTimesStopped = 0;
        hasChangedEngine = false;
        needsToChangeEngine = false;
        randomGenerator = new Random();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                driveToNextCrossing();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            if (carNeedsTiresChanged()) {
                System.out.println("Car needs tires changed at intersection " + currentIntersection.getLabel());
                currentIntersection.stopForEmergencyTiresChange(this);
                environmentHq.addCarThatNeedsTiresChanged(getCarInfo());
                synchronized (label) {
                    try {
                        label.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                environmentHq.notifyOfTiresChange(getCarInfo());
                currentIntersection.leaveAfterEmergencyTiresChange(this);
            }

            if (interSectionHasCarService()) {
                try {
                    goToCarService();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (carMustUpdateEnvironmentHqWithNewInfo()) {
                environmentHq.updateEnvironmentHqWithCarInformation(getCarInfo());
            }
            if (carMustAskPermissionFromEnvironmentHqToDrive()) {
                try {
                    boolean wasStopped = environmentHq.blockUntilCarAllowedToDrive(label);
                    if (wasStopped) {
                        numberOfTimesStopped++;
                        decideIfEngineNeedsToBeUpgraded();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private boolean carNeedsTiresChanged() {
        return !tires.areHealthy();
    }

    private void goToCarService() throws InterruptedException {

        if (needsToChangeEngine) {
            currentIntersection.getCarService().get().serveCarWithEngineChange(this);
            hasChangedEngine = true;
            needsToChangeEngine = false;
            environmentHq.notifyOfEngineChange(getCarInfo());
        } else {
            currentIntersection.getCarService().get().serveCar(this);
        }
    }

    private void decideIfEngineNeedsToBeUpgraded() {
        if (numberOfTimesStopped >= MIN_TIMES_STOPPED_BEFORE_ENGINE_UPGRADE && !hasChangedEngine && !needsToChangeEngine) {
            if (randomGenerator.nextFloat() <= CHANCE_TO_UPGRADE_ENGINE) {
                System.out.println("Car decided to change engine!");
                needsToChangeEngine = true;
            } else {
                System.out.println("Car did not decide to change engine!");
            }
        }
    }

    private boolean interSectionHasCarService() {
        return currentIntersection.getCarService().isPresent();
    }


    private boolean carMustUpdateEnvironmentHqWithNewInfo() {
        return numberOfDrivenStreets % UPDATE_HQ_EVERY_N_STREETS == 0;
    }

    private boolean carMustAskPermissionFromEnvironmentHqToDrive() {
        return numberOfDrivenStreets % ASK_ALLOWED_TO_DRIVE_EVERY_N_STREETS == 0;
    }

    public void driveToNextCrossing() throws InterruptedException {
        long timeToSleep = MIN_TIME_TO_DRIVE_MS +
                randomGenerator.nextInt(MAX_TIME_TO_DRIVE_MS - MIN_TIME_TO_DRIVE_MS + 1);
        Thread.sleep(timeToSleep);

        List<Intersection> adjIntersections = streetNetwork.getAdjIntersections(currentIntersection);
        Intersection nextIntersection = adjIntersections.get(randomGenerator.nextInt(adjIntersections.size()));
        Street drivenStreet = streetNetwork.getStreet(currentIntersection, nextIntersection);
        updateHealthOfTires(drivenStreet);
        //System.out.println("Car " + label + " travelled from " + currentIntersection + " to " + nextIntersection);
        currentIntersection = nextIntersection;
        numberOfDrivenStreets++;
    }

    private void updateHealthOfTires(Street drivenStreet) {
        tires.reduceHealth(drivenStreet);
    }

    public CarInformation getCarInfo() {
        return new CarInformation(label, tires, engine, numberOfDrivenStreets, currentIntersection);
    }

    public void setIntersection(Intersection intersection) {
        this.currentIntersection = intersection;
    }

    public void setEnvironmentHq(EnvironmentHq environmentHq) {
        this.environmentHq = environmentHq;
    }

    public void setStreetNetwork(StreetNetwork streetNetwork) {
        this.streetNetwork = streetNetwork;
    }

    public int getLabel() {
        return label;
    }

    public int getNumberOfDrivenStreets() {
        return numberOfDrivenStreets;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public Intersection getCurrentIntersection() {
        return currentIntersection;
    }

    public EnvironmentHq getEnvironmentHq() {
        return environmentHq;
    }

    public void repairTires(Tires newTires) {
        synchronized (label) {
            tires = newTires;
            label.notify();
        }

    }
}
