package kodutoo12.storage;

import kodutoo12.orders.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderStorage {

    private List<Order> orders;

    public OrderStorage() {
        this.orders = new ArrayList<>();
    }

    public synchronized void addOrder(Order order) {
        orders.add(order);
        notifyAll();
    }

    public synchronized Order getOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        return orders.remove(0);
    }

    public synchronized boolean hasNextOrder() {
        return !orders.isEmpty();
    }

    public int getSize() {
        return orders.size();
    }
}
