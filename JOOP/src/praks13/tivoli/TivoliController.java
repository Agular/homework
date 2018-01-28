package praks13.tivoli;

import praks13.caroussel.Caroussel;
import praks13.caroussel.CarousselMonitor;
import praks13.ticket.TicketStorage;
import praks13.ticketvendor.TicketVendor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TivoliController {

    public void runSystem() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        TicketStorage ticketStorage = new TicketStorage();

        TicketVendor ticketVendorOne = new TicketVendor("Alpha", 2);
        TicketVendor ticketVendorSecond = new TicketVendor("Omega", 3);

        ticketVendorOne.setTicketStorage(ticketStorage);
        ticketVendorSecond.setTicketStorage(ticketStorage);

        CarousselMonitor carousselMonitor = new CarousselMonitor();

        Caroussel happyCaroussel = new Caroussel("Happy", carousselMonitor);
        Caroussel coolCaroussel = new Caroussel("Cool", carousselMonitor);
        Caroussel chillCaroussel = new Caroussel("Chill", carousselMonitor);

        happyCaroussel.setTicketStorage(ticketStorage);
        coolCaroussel.setTicketStorage(ticketStorage);
        chillCaroussel.setTicketStorage(ticketStorage);

        Future<Integer> happyPeopleWeight = executorService.submit(happyCaroussel);
        Future<Integer> coolPeopleWeight = executorService.submit(coolCaroussel);
        Future<Integer> chillPeopleWeight = executorService.submit(chillCaroussel);

        Future<Integer> firstVendorTickets = executorService.submit(ticketVendorOne);
        Future<Integer> secondVendorTickets = executorService.submit(ticketVendorSecond);


        while(!happyPeopleWeight.isDone()){ }
        System.out.println("Happy Caroussel had average weight of: " + happyPeopleWeight.get());
        while(!coolPeopleWeight.isDone()){ }
        System.out.println("Cool Caroussel had average weight of: " + coolPeopleWeight.get());
        while(!chillPeopleWeight.isDone()){ }
        System.out.println("Chill Caroussel had average weight of: " + chillPeopleWeight.get());

        executorService.shutdownNow();

        int totalTicketsSold = firstVendorTickets.get() + secondVendorTickets.get();
        System.out.println("The ticket vendors sold total of " + totalTicketsSold + " tickets!");
    }
}
