package praks13.caroussel;

public class CarousselMonitor {

    private final int MAX_WEIGHT_ON_ALL_CAROUSSELS = 1300;
    private int peopleWeightOnAllCaroussels = 0;

    public synchronized void addPeopleWeightToMonitor(int peopleWeight) throws InterruptedException {
        while (peopleWeightOnAllCaroussels >= MAX_WEIGHT_ON_ALL_CAROUSSELS) {
            wait();
        }
        peopleWeightOnAllCaroussels += peopleWeight;
    }

    public synchronized void removePeopleWeightFromMonitor(int peopleWeight) {
        peopleWeightOnAllCaroussels -= peopleWeight;
        notifyAll();
    }
}
