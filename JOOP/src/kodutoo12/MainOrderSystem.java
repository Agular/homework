package kodutoo12;

import kodutoo12.controller.OrderController;

public class MainOrderSystem {

    public static void main(String[] args) throws InterruptedException {
        OrderController orderController = new OrderController();
        orderController.runSystem();
    }
}
