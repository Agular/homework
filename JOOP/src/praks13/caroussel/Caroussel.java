package praks13.caroussel;

import praks13.ticket.Ticket;
import praks13.ticket.TicketStorage;

import java.util.concurrent.Callable;

public class Caroussel implements Callable<Integer> {


    private CarousselMonitor carousselMonitor;

    private TicketStorage ticketStorage;
    private String id;
    private int servedPeople;
    private int servedPeopleWeight;
    private int peopleWeightOnCaroussel = 0;
    private int peopleInCaroussel = 0;
    private final int MAX_SERVABLE_PEOPLE_PER_DAY_ON_HEAVY_LOAD = 5000;
    private final int MAX_SERVABLE_PEOPLE_PER_DAY_ON_NORMAL_LOAD = 7000;
    private final int AVG_PERSON_WEIGHT = 65;
    private final int MAX_PEOPLE_PER_RIDE = 10;
    private final int TIME_TO_WORK_MS = 67;

    public Caroussel(String id, CarousselMonitor carousselMonitor) {
        servedPeople = 0;
        servedPeopleWeight = 0;
        this.id = id;
        this.carousselMonitor = carousselMonitor;
    }

    public void setTicketStorage(TicketStorage ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    private void registerTicket() throws InterruptedException {
        Ticket ticket = ticketStorage.getTicket();
        servedPeople++;
        servedPeopleWeight += ticket.getWeight();
        peopleWeightOnCaroussel += ticket.getWeight();
        peopleInCaroussel++;
        System.out.println("CarousselID: " + id + "    TicketID: " + ticket.getId() + " Weight: " + ticket.getWeight());
    }

    private void takePeopleToFunRide() throws InterruptedException {
        Thread.sleep(TIME_TO_WORK_MS);
    }

    private boolean carousselIsAbleToWork() {
        return servedPeople < MAX_SERVABLE_PEOPLE_PER_DAY_ON_NORMAL_LOAD && avgWeightOfAllPeople() < AVG_PERSON_WEIGHT ||
                servedPeople < MAX_SERVABLE_PEOPLE_PER_DAY_ON_HEAVY_LOAD && avgWeightOfAllPeople() >= AVG_PERSON_WEIGHT;
    }

    private int avgWeightOfAllPeople() {
        if (servedPeople == 0) {
            return 0;
        } else {
            return servedPeopleWeight / servedPeople;
        }
    }

    @Override
    public Integer call() throws Exception {
        while (carousselIsAbleToWork()) {
            while (peopleInCaroussel != MAX_PEOPLE_PER_RIDE) {
                try {
                    registerTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                carousselMonitor.addPeopleWeightToMonitor(peopleWeightOnCaroussel);
                takePeopleToFunRide();
                carousselMonitor.removePeopleWeightFromMonitor(peopleWeightOnCaroussel);
                peopleWeightOnCaroussel = 0;
                peopleInCaroussel = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Caroussel ID: " + id + " has served " + servedPeople + " people");
        return avgWeightOfAllPeople();
    }
}
