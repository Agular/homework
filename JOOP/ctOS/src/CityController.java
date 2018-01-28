import car.Car;
import car.Cars;
import city.City;
import city.carfacilities.CarService;
import city.inhabitants.Bird;
import streetnetwork.Intersection;
import streetnetwork.Street;
import streetnetwork.StreetNetwork;

import java.util.ArrayList;
import java.util.List;

public class CityController {

    private final int AMOUNT_OF_ALL_CARS = 200;
    private final int AMOUNT_OF_BIOFRIENDLY_CARS = 20;

    public void runSystem() {

        Intersection intersection1 = new Intersection(1, true);
        Intersection intersection2 = new Intersection(2);
        Intersection intersection3 = new Intersection(3);
        Intersection intersection4 = new Intersection(4);
        intersection4.setCarService(new CarService("4"));
        Intersection intersection5 = new Intersection(5);
        Intersection intersection6 = new Intersection(6);
        Intersection intersection7 = new Intersection(7);
        intersection7.setCarService(new CarService("7"));
        Intersection intersection8 = new Intersection(8);
        Intersection intersection9 = new Intersection(9, true);
        Intersection intersection10 = new Intersection(10);
        intersection10.setCarService(new CarService("10"));
        Intersection intersection11 = new Intersection(11);
        Intersection intersection12 = new Intersection(12);
        Intersection intersection13 = new Intersection(13, true);
        Intersection intersection14 = new Intersection(14);
        intersection4.setCarService(new CarService("14"));
        Intersection intersection15 = new Intersection(15);
        Intersection intersection16 = new Intersection(16);
        Intersection intersection17 = new Intersection(17, true);


        StreetNetwork streetNetwork = new StreetNetwork();

        streetNetwork.addStreet(new Street(intersection1, intersection2, "Alfa"));
        streetNetwork.addStreet(new Street(intersection2, intersection3, "Bravo"));
        streetNetwork.addStreet(new Street(intersection2, intersection4, "Charlie"));
        streetNetwork.addStreet(new Street(intersection2, intersection5, "Delta"));
        streetNetwork.addStreet(new Street(intersection3, intersection4, "Echo"));
        streetNetwork.addStreet(new Street(intersection3, intersection5, true, "Foxtrot"));
        streetNetwork.addStreet(new Street(intersection4, intersection5, "Golf"));
        streetNetwork.addStreet(new Street(intersection4, intersection6, true, "Hotel"));
        streetNetwork.addStreet(new Street(intersection6, intersection7, "India"));
        streetNetwork.addStreet(new Street(intersection6, intersection14, "Juliett"));
        streetNetwork.addStreet(new Street(intersection7, intersection8, "Kilo"));
        streetNetwork.addStreet(new Street(intersection7, intersection9, "Lima"));
        streetNetwork.addStreet(new Street(intersection5, intersection10, "Mike"));
        streetNetwork.addStreet(new Street(intersection10, intersection11, "November"));
        streetNetwork.addStreet(new Street(intersection10, intersection14, "Oscar"));
        streetNetwork.addStreet(new Street(intersection11, intersection12, "Papa"));
        streetNetwork.addStreet(new Street(intersection12, intersection13, "Romeo"));
        streetNetwork.addStreet(new Street(intersection14, intersection15, "Sierra"));
        streetNetwork.addStreet(new Street(intersection14, intersection16, "Tango"));
        streetNetwork.addStreet(new Street(intersection16, intersection17, "Uniform"));

        City city = new City("Codename Sanctuary", streetNetwork);

        List<Runnable> cars = new ArrayList<>();
        for (int i = 0; i < AMOUNT_OF_ALL_CARS - AMOUNT_OF_BIOFRIENDLY_CARS; i++) {
            cars.add(Cars.newInstaceOfRegularCar());
        }
        for (int i = 0; i < AMOUNT_OF_BIOFRIENDLY_CARS; i++) {
            cars.add(Cars.newInstanceOfBioFriendlyCar());
        }
        List<Thread> carThreads = new ArrayList<>();
        for (Runnable car : cars) {
            city.registerCar((Car) car);
            carThreads.add(new Thread(car));
        }

        Runnable testCar = Cars.newInstaceOfRegularCar();
        city.registerCar((Car) testCar);
        Thread testCarThread = new Thread(testCar);
        testCarThread.start();

        //carThreads.forEach(Thread::start);

        Runnable bird = new Bird(city);
        Thread birdThread = new Thread(bird);
        birdThread.start();


    }
}
