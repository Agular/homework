package kodutoo12.controller;

import kodutoo12.orderreader.OrderReader;
import kodutoo12.orderservice.OrderService;
import kodutoo12.storage.OrderStorage;

import java.util.ArrayList;
import java.util.List;

public class OrderController {

    public void runSystem() throws InterruptedException {

        OrderStorage orderStorage = new OrderStorage();
        OrderService orderService = new OrderService("service 1", orderStorage);
        List<Thread> orderReaderThreads = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            orderReaderThreads.add(new Thread(new OrderReader(String.valueOf(i), orderStorage)));
        }
        Thread serviceThread = new Thread(orderService);

        serviceThread.start();

        for (Thread readerThread : orderReaderThreads) {
            readerThread.start();
        }

        serviceThread.join();


        while (orderStorage.getSize() != 0){}

        for (Thread readerThread : orderReaderThreads) {
            readerThread.interrupt();
        }
        for (Thread readerThread : orderReaderThreads) {
            readerThread.join();
        }

        // soovitusik kasutada executorit, awaitterminationit ning shutdownnow --> 12. praksi tunnis, vaata üle
    }
}
